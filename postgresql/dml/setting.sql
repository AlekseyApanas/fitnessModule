INSERT INTO fitness.role
VALUES ('ADMIN'),('USER');

INSERT INTO fitness.status
VALUES ('ACTIVATED'),('WAITING_ACTIVATION'),('DEACTIVATED');


INSERT INTO fitness.user
     VALUES ('4c08a724-a477-4015-87e6-e8357fbec3e7',
      now(),
      now(),
      'admin',
      'admin@mail.ru',
      '123');

INSERT INTO fitness.user_role
	 VALUES ('ADMIN','4c08a724-a477-4015-87e6-e8357fbec3e7');

INSERT INTO fitness.user_status
	 VALUES ('ACTIVATED','4c08a724-a477-4015-87e6-e8357fbec3e7');