DELIMITER $$
DROP PROCEDURE IF EXISTS get_user_details $$

CREATE PROCEDURE get_user_details(
    IN p_email VARCHAR(100)
  )

BEGIN

    SELECT * FROM user WHERE email = p_email;

END $$

DELIMITER $$


