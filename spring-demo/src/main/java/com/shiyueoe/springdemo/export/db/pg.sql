CREATE TABLE demo_data (
                           id BIGINT PRIMARY KEY,
                           name VARCHAR(100),
                           category VARCHAR(50),
                           created_at TIMESTAMP,
                           updated_at TIMESTAMP
);


INSERT INTO demo_data (id, name, category, created_at, updated_at)
SELECT
    gs AS id,
    md5(random()::text) AS name,
    CASE (random() * 3)::int
        WHEN 0 THEN 'A'
        WHEN 1 THEN 'B'
        WHEN 2 THEN 'C'
        ELSE 'D'
        END AS category,
    NOW() - (random() * interval '365 days') AS created_at,
    NOW() - (random() * interval '30 days') AS updated_at
FROM generate_series(1, 1000000) gs;
