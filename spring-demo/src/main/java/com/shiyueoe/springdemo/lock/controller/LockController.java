package com.shiyueoe.springdemo.lock.controller;


import com.shiyueoe.springdemo.lock.entity.CreateReq;
import com.shiyueoe.springdemo.lock.entity.GrabResponse;
import com.shiyueoe.springdemo.lock.entity.RedPacket;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("red-packet")
public class LockController {

    private final AtomicLong idGen = new AtomicLong(1);
    private final Random random = new Random();
    private final Map<Long, RedPacket> store = new ConcurrentHashMap<>();

    @PostMapping("create")
    public String create(@RequestBody CreateReq req){
        if(req.getTotal() < 0 || req.getPartCount() < 0){
            return "参数错误，红包个数/金额不得小于0";
        }
        int totalCents = (int) (req.getTotal() * 100);
        List<Integer> portions = splitRandom(totalCents, req.getPartCount());
        long id = idGen.getAndIncrement();
        RedPacket rp = new RedPacket();
        rp.setId(id);
        rp.setTotalCents(totalCents);
        rp.setParts(new LinkedList<>(portions));
        store.put(id, rp);
        return String.valueOf(id);
    }

    @GetMapping("grab")
    public GrabResponse grab(@RequestParam("userId")Long userId, @RequestParam("packetId") Long packetId){
        RedPacket redPacket = store.get(packetId);

        GrabResponse response = new GrabResponse();
        response.setSuccess(false);
        response.setAmount(0d);
        response.setUserId(userId);
        if(redPacket == null){
            response.setMessage("红包不存在");
            return response;
        }

        Integer money = redPacket.getParts().poll();
        if(money == null){
            response.setMessage("红包已抢完");
            return response;
        }

        double yuan = money / 100.0;

        response.setSuccess(true);
        response.setAmount(yuan);
        response.setMessage("抢红包成功");
        return response;
    }

    // ---------- 辅助方法 ----------
    /**
     * 随机拆分 totalCents 为 n 份（单位：分），每份至少 1 分。
     * 使用：随机生成 n-1 个切点（1 ~ total-1），排序后差值即每份金额。
     */
    private List<Integer> splitRandom(int totalCents, int n) {
        if (n <= 0) throw new IllegalArgumentException("n must > 0");
        if (n == 1) return Collections.singletonList(totalCents);
        if (totalCents < n) {
            // 每份至少 1 分，不够分的情况下：给每份 1 分，余下为 0（极端情况）
            List<Integer> out = new ArrayList<>(Collections.nCopies(n, 1));
            return out;
        }
        // 生成 n-1 个不同的随机切点
        Set<Integer> cuts = new HashSet<>();
        while (cuts.size() < n - 1) {
            int r = random.nextInt(totalCents - 1) + 1; // [1, totalCents-1]
            cuts.add(r);
        }
        List<Integer> cutList = new ArrayList<>(cuts);
        Collections.sort(cutList);
        List<Integer> parts = new ArrayList<>();
        int prev = 0;
        for (int c : cutList) {
            parts.add(c - prev);
            prev = c;
        }
        parts.add(totalCents - prev);
        // 将结果按随机顺序打乱（更像实际群抢）
        Collections.shuffle(parts, random);
        return parts;
    }
}