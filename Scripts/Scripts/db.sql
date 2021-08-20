---------------------Scripts For Database------------------------
CREATE TABLE gateway (
id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
serial_number VARCHAR(100) NOT NULL,
name VARCHAR(200) NOT NULL,
IPv4_address  VARCHAR(300)
)

CREATE TABLE peripheral_device (
id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
uid INT(6) NOT NULL,
vendor VARCHAR(200) NOT NULL,
created_date date NOT NULL,
status  VARCHAR(50) NOT NULL,
gateway_id INT(6) UNSIGNED NOT NULL,
CONSTRAINT peripheral_device_fk FOREIGN KEY (gateway_id)
    REFERENCES gateway(id)
)

ALTER TABLE peripheral_device
ADD gateway_id INT(6);

ALTER TABLE peripheral_device
ADD CONSTRAINT peripheral_device_fk
FOREIGN KEY (gateway_id) REFERENCES gateway(id);