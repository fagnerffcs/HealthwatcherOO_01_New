-- healthunit info
INSERT INTO healthunit(code, description) VALUES (0, 'UPA Olinda (Bairro Novo)');
INSERT INTO healthunit(code, description) VALUES (1, 'Hospital Português, Recife');

--medical specialty info
INSERT INTO medicalspecialty(code, description) VALUES (0, 'Urology');
INSERT INTO medicalspecialty(code, description) VALUES (1, 'Cardiology');
INSERT INTO medicalspecialty(code, description) VALUES (2, 'Oncology');
INSERT INTO medicalspecialty(code, description) VALUES (3, 'Psicotherapy');
INSERT INTO medicalspecialty(code, description) VALUES (4, 'Odontology');

-- healthunit x medical specialty info
INSERT INTO healthunit_medicalspecialty(healthunit_code, medicalspecialty_code) VALUES (0, 0);
INSERT INTO healthunit_medicalspecialty(healthunit_code, medicalspecialty_code) VALUES (0, 3);
INSERT INTO healthunit_medicalspecialty(healthunit_code, medicalspecialty_code) VALUES (0, 4);

INSERT INTO healthunit_medicalspecialty(healthunit_code, medicalspecialty_code) VALUES (1, 0);
INSERT INTO healthunit_medicalspecialty(healthunit_code, medicalspecialty_code) VALUES (1, 1);
INSERT INTO healthunit_medicalspecialty(healthunit_code, medicalspecialty_code) VALUES (1, 2);
INSERT INTO healthunit_medicalspecialty(healthunit_code, medicalspecialty_code) VALUES (1, 4);