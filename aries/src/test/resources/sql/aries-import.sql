insert into UserAccounts (chrStaffNumber, chrSurname, chrFirstname) values ('u5125986','Turner','Genevieve');
insert into UserAccounts (chrStaffNumber, chrSurname, chrFirstname) values ('u1234567','Khanna','Rahul');
insert into externalUsers (chrCode, chrSurname, chrFirstname, chrInstitutionName) values ('E1111','Body','Some','University of New South Wales');
insert into externalUsers (chrCode, chrSurname, chrFirstname, chrInstitutionName) values ('E1112','Person','Random','Sydney University');

insert into FOR_codes (chrForDivisionCode, chrForGroupCode, chrForObjectiveCode, chrForDescription) values ('11','1117','111706','Epidemiology');
insert into FOR_codes (chrForDivisionCode, chrForGroupCode, chrForObjectiveCode, chrForDescription) values ('02','0201','020102','Astronomical and Space Instrumentation');
insert into FOR_codes (chrForDivisionCode, chrForGroupCode, chrForObjectiveCode, chrForDescription) values ('03','0303','030303','Optical Properties of Materials');
insert into FOR_codes (chrForDivisionCode, chrForGroupCode, chrForObjectiveCode, chrForDescription) values ('04','0401','040106','Cloud Physics');

insert into SEO_codes (chrSubdivision, chrSubdivisionCode, chrGroupCode, chrClassCode) values ('Scientific Instruments', '86', '8615', '861503');
insert into SEO_codes (chrSubdivision, chrSubdivisionCode, chrGroupCode, chrClassCode) values ('Expanding Knowledge in the Physical Sciences', '97', '9701', '970102');

insert into FacultySchoolCentre (chrTier2Code, chrTier2Name) values ('ES','Enterprise Services');
insert into Colleges (chrCollegeCode, chrCollegeName) values ('ITS','Information Technology Services');
insert into Institutions (chrTier1Code, chrTier1Name) values ('ANU','The Australian National University');
insert into Departments (chrTier3Code, chrTier3Name, chrTier2Code, chrCollegeCode, chrTier1Code) values ('1','eResearch Support and Development', 'ES','ITS','ANU');

insert into Research_outputs_journals_publishers (intPublisherID, chrPublisher) values (1,'Elsevier');
insert into Research_outputs_journals_publishers (intPublisherID, chrPublisher) values (2,'New South Publishing');

insert into Research_outputs_journals (intJournalCode, chrISSN, chrJournalName, intPublisherID) values (1, '1234-5678','Journal of all', 1);
insert into Research_outputs_data1(chrApplicationDescription, chrConferencePaperRefereed, chrCreatedByCode, chrFORcode1, chrFORcode2, chrFORcode3, chrIssue, chrOutput1Code, chrOutput2Code, chrOutput6Code, chrPageNumbers, chrPublicationTitle, chrReportingYear, chrScopusID, chrSEOncode1, chrSEOncode2, chrThompsonID, chrUrlArticle, chrVolume, intJournalCode) values ('Journal abstract example','yes','u1111111','020102','030303','040106','21','RO2','RL2','u1111111xPUB2','57-59','Test Journal Title','2013','1111111112','861503','970102','9111111112','http://google.com.au','12',1)

insert into Research_outputs_data_authors (chrOutputInvestigatorCode, chrOutput6Code, chrStaffNumber, chrRoute, chrOrder) values ('u1111111xPUB2xE1111External', 'u1111111xPUB2','E1111','External','1');
insert into Research_outputs_data_authors (chrOutputInvestigatorCode, chrOutput6Code, chrStaffNumber, chrRoute, chrOrder) values ('u1111111xPUB2xu1111111Internal', 'u1111111xPUB2','u1111111','Internal','2');
insert into Research_outputs_data_authors (chrOutputInvestigatorCode, chrOutput6Code, chrStaffNumber, chrRoute, chrOrder) values ('u1111111xPUB2xE1112External', 'u1111111xPUB2','E1112','External','3');
insert into Research_outputs_data_authors (chrOutputInvestigatorCode, chrOutput6Code, chrStaffNumber, chrRoute, chrOrder) values ('u1111111xPUB2xu1234567Internal', 'u1111111xPUB2','u1234567','Internal','4');

insert into Research_outputs_data_authors_departments (chrOutputDepartmentCode, chrDepartmentCode, chrOutputInvestigatorCode) values ('u1111111xPUB2xu1111111Internalx1','1','u1111111xPUB2xu1111111Internal');
insert into Research_outputs_data_authors_departments (chrOutputDepartmentCode, chrDepartmentCode, chrOutputInvestigatorCode) values ('u1111111xPUB2xu1234567Internalx1','1','u1111111xPUB2xu1234567Internal');

insert into Research_outputs_notes (chrOutputsNotesCode, chrOutput6Code, intOutputNotesCounter, intNotesType, chrNotes) values ('u1111111xPUB2/1','u1111111xPUB2',1, 3,'Copyright Elsevier'); -- Copyright information
insert into Research_outputs_notes (chrOutputsNotesCode, chrOutput6Code, intOutputNotesCounter, intNotesType, chrNotes) values ('u1111111xPUB2/2','u1111111xPUB2',2, 5,'Testing'); -- Keywords

insert into Research_outputs_data_documents (chrOutputDocumentCode, chrOutput6Code, intOutputDocumentCounter, chrDocumentName, chrURL) values ('u1111111xPUB2xDOC1','u1111111xPUB2',1,'ANU Repository Link','http://hdl.handle.net/1885/2');
--insert into Research_outputs_data_documents (chrOutputDocumentCode, chrOutput6Code, intOutputDocumentCounter, chrDocumentName, chrURL) values ('u1111111xPUB2xDOC1','u1111111xPUB2',1,null,'http://hdl.handle.net/1885/2');
