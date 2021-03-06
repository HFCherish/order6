CREATE TABLE payments (
  order_id BIGINT NOT NULL,
  type VARCHAR(255) DEFAULT "CREDIT_CARD",
  amount DOUBLE NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (order_id) REFERENCES orders(id)
);