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

--diseasetype info
INSERT INTO diseasetype(code, description, duration, manifestation, name) VALUES (0, 'Influenza virus', '3 weeks', 'All year', 'Flu');
INSERT INTO diseasetype(code, description, duration, manifestation, name) VALUES (1, 'Sarcoidosis', '1 day', 'All year', 'Sarcoidosis');
INSERT INTO diseasetype(code, description, duration, manifestation, name) VALUES (2, 'Migraine', '2 to 72 hours', 'mixture of environmental and genetic factors', 'Migraine');

--symptom info
INSERT INTO symptom(code, description) VALUES (0, 'Cough');
INSERT INTO symptom(code, description) VALUES (1, 'Fever');
INSERT INTO symptom(code, description) VALUES (2, 'Dry throat');
INSERT INTO symptom(code, description) VALUES (3, 'Headache');
INSERT INTO symptom(code, description) VALUES (4, 'Muscle pain');

--diseasetype_symptom info
INSERT INTO diseasetype_symptom(diseasetype_code, symptom_code) VALUES (0, 0);
INSERT INTO diseasetype_symptom(diseasetype_code, symptom_code) VALUES (0, 1);
INSERT INTO diseasetype_symptom(diseasetype_code, symptom_code) VALUES (0, 2);
INSERT INTO diseasetype_symptom(diseasetype_code, symptom_code) VALUES (0, 3);
INSERT INTO diseasetype_symptom(diseasetype_code, symptom_code) VALUES (1, 4);
INSERT INTO diseasetype_symptom(diseasetype_code, symptom_code) VALUES (2, 3);