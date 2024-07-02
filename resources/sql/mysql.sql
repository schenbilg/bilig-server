-- :name user-register! :! :n
-- :doc register a new user record
INSERT INTO c_user
(user_id,login_name,login_pwd,nick_name,gender,phone,remark)
VALUES (:user_id, :login_name, :login_pwd, :nick_name, :gender, :phone, :remark)

-- :name create-user! :! :n
-- :doc creates a new user record
    INSERT INTO users
(id, first_name, last_name, email, pass)
VALUES (:id, :first_name, :last_name,http-kit :email, :pass)