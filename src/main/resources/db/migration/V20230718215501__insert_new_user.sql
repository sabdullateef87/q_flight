DELIMITER $$

DROP PROCEDURE IF EXISTS insert_new_user $$

CREATE PROCEDURE insert_new_user(
    IN p_first_name VARCHAR(50),
    IN p_last_name VARCHAR(50),
    IN p_email VARCHAR(100),
    IN p_password VARCHAR(225),
    IN p_date_of_birth VARCHAR(50),
    IN p_address VARCHAR(225),
    IN p_image VARCHAR(225),
    IN p_mobile_number VARCHAR(15)
)

BEGIN
	DECLARE saved_id INT;
    INSERT INTO user (first_name, last_name, email, password, date_of_birth, ddress, image, mobile_number)
    VALUES(p_first_name, p_last_name, p_email, p_password, p_date_of_birth, p_address, p_image, p_mobile_number);
    SET saved_id = LAST_INSERTED_ID();

    SELECT * FROM user WHERE id = saved_id;

END $$

DELIMITER $$