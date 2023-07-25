DELIMITER $$
DROP PROCEDURE IF EXISTS get_user_details $$

CREATE PROCEDURE get_user_details(
    IN p_email VARCHAR(100),
    IN p_mobile_number VARCHAR(15)
)

BEGIN

    SELECT * FROM user WHERE
		(p_email IS NULL OR email = p_email) AND
        (p_mobile_number IS NULL OR mobile_number = p_mobile_number);

END $$

DELIMITER $$


