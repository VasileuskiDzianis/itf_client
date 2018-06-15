CREATE TABLE bids (
  id BIGINT NOT NULL AUTO_INCREMENT,
  request VARCHAR(255),
  bid DECIMAL(10,4),
  due_date DATE,
  bid_status VARCHAR(20),
  PRIMARY KEY (id)
);