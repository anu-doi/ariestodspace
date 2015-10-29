insert into UserAccounts (chrStaffNumber, chrSurname, chrFirstname) values ('u1111111','Turner','Genevieve');
insert into UserAccounts (chrStaffNumber, chrSurname, chrFirstname) values ('u1111112','Khanna','Rahul');
insert into UserAccounts (chrStaffNumber, chrSurname, chrFirstname) values ('u1111113','Kheifets','Anatoli');
insert into UserAccounts (chrStaffNumber, chrSurname, chrFirstname) values ('u1111114','Butterworth','Peter');
insert into UserAccounts (chrStaffNumber, chrSurname, chrFirstname) values ('u1111115','Anstey','Kaarin');
insert into UserAccounts (chrStaffNumber, chrSurname, chrFirstname) values ('u1111116','Crisp','Dimity');
insert into UserAccounts (chrStaffNumber, chrSurname, chrFirstname) values ('u1111117','Strauss','Bernard');

insert into externalUsers (chrCode, chrSurname, chrFirstname, chrInstitutionName) values ('E1111','Body','Some','University of New South Wales');
insert into externalUsers (chrCode, chrSurname, chrFirstname, chrInstitutionName) values ('E1112','Person','Random','Sydney University');
insert into externalUsers (chrCode, chrSurname, chrFirstname, chrInstitutionName) values ('E1113','Bray','Igor','University of Melbourne');
insert into externalUsers (chrCode, chrSurname, chrFirstname, chrInstitutionName) values ('E1114','Windsor','Tim','University of Somewhere');

insert into FacultySchoolCentre (chrTier2Code, chrTier2Name) values ('ES','Enterprise Services');
insert into Colleges (chrCollegeCode, chrCollegeName) values ('ITS','Information Technology Services');
insert into Institutions (chrTier1Code, chrTier1Name) values ('ANU','The Australian National University');
insert into Departments (chrTier3Code, chrTier3Name, chrTier2Code, chrCollegeCode, chrTier1Code) values ('1','eResearch Support and Development', 'ES','ITS','ANU');

insert into FOR_codes (chrForDivisionCode, chrForGroupCode, chrForObjectiveCode, chrForDescription) values ('01','0105','010501','Algebraic Structures in Mathematical Physics');
insert into FOR_codes (chrForDivisionCode, chrForGroupCode, chrForObjectiveCode, chrForDescription) values ('02','0201','020102','Astronomical and Space Instrumentation');
insert into FOR_codes (chrForDivisionCode, chrForGroupCode, chrForObjectiveCode, chrForDescription) values ('02','0202','020201','Atomic and Molecular Physics');
insert into FOR_codes (chrForDivisionCode, chrForGroupCode, chrForObjectiveCode, chrForDescription) values ('02','0206','020602','Field Theory and String Theory');
insert into FOR_codes (chrForDivisionCode, chrForGroupCode, chrForObjectiveCode, chrForDescription) values ('03','0303','030303','Optical Properties of Materials');
insert into FOR_codes (chrForDivisionCode, chrForGroupCode, chrForObjectiveCode, chrForDescription) values ('04','0401','040106','Cloud Physics');
insert into FOR_codes (chrForDivisionCode, chrForGroupCode, chrForObjectiveCode, chrForDescription) values ('04','0403','040314','Volcanology');
insert into FOR_codes (chrForDivisionCode, chrForGroupCode, chrForObjectiveCode, chrForDescription) values ('09','0909','090901','Cartography');

insert into SEO_codes (chrSubdivision, chrSubdivisionCode, chrGroupCode, chrClassCode) values ('Intelligence','81','8101','810105');
insert into SEO_codes (chrSubdivision, chrSubdivisionCode, chrGroupCode, chrClassCode) values ('First Stage Treatment of Ores and Minerals not elsewhere classified','84','8403','840399');
insert into SEO_codes (chrSubdivision, chrSubdivisionCode, chrGroupCode, chrClassCode) values ('Industrial Instruments','86','8615','861501');
insert into SEO_codes (chrSubdivision, chrSubdivisionCode, chrGroupCode, chrClassCode) values ('Scientific Instruments','86','8615','861503');
insert into SEO_codes (chrSubdivision, chrSubdivisionCode, chrGroupCode, chrClassCode) values ('Mountain and High Country Land and Water Management','96','9609','960909');
insert into SEO_codes (chrSubdivision, chrSubdivisionCode, chrGroupCode, chrClassCode) values ('Expanding Knowledge in the Physical Sciences','97','9701','970102');

insert into Research_outputs_journals_publishers (intPublisherID, chrPublisher) values (1,'Elsevier');
insert into Research_outputs_journals_publishers (intPublisherID, chrPublisher) values (2,'New South Publishing');
insert into Research_outputs_journals_publishers (intPublisherID, chrPublisher) values (3,'American Physical Society');
insert into Research_outputs_journals_publishers (intPublisherID, chrPublisher) values (4,'Wiley');

insert into Research_outputs_books (intBookCode, chrBookName, chrISBN, chrEditor, chrEdition, intPublisherID) values (1, 'The book of everything','978-1234-5678','Some Body & Test Person','1st',2);
insert into Research_outputs_data1(chrApplicationDescription, chrConferencePaperRefereed, chrCreatedByCode, chrFORcode1, chrFORcode2, chrFORcode3, chrOutput1Code, chrOutput2Code, chrOutput6Code, chrPageNumbers, chrPublicationTitle, chrReportingYear, chrScopusID, chrSEOncode1, chrSEOncode2, chrSEOncode3, chrSeriesTitle, chrThompsonID, chrUrlArticle, intBookCode) values ('Abstract example', 'yes', 'u1111111','010501','020602','040314','RO1','RL4','u1111111xPUB1','146-156','Test Book Chapter','2011','1111111111','810105','840399','861501','Book Series 1','9111111111','http://dx.doi.org/10.1007/978-3-319-09804-3__7', 1)

insert into Research_outputs_data_authors (chrOutputInvestigatorCode, chrOutput6Code, chrStaffNumber, chrRoute, chrOrder) values ('u1111111xPUB1xu1111111Internal','u1111111xPUB1','u1111111','Internal','1');

insert into Research_outputs_data_authors_departments (chrOutputDepartmentCode, chrDepartmentCode, chrOutputInvestigatorCode) values ('u1111111xPUB1xu1111111Internalx1','1','u1111111xPUB1xu1111111Internal');

insert into Research_outputs_journals (intJournalCode, chrISSN, chrJournalName, intPublisherID) values (1, '1234-5678','Journal of all', 1);
insert into Research_outputs_data1(chrApplicationDescription, chrConferencePaperRefereed, chrCreatedByCode, chrFORcode1, chrFORcode2, chrFORcode3, chrIssue, chrOutput1Code, chrOutput2Code, chrOutput6Code, chrPageNumbers, chrPublicationTitle, chrReportingYear, chrScopusID, chrSEOncode1, chrSEOncode2, chrThompsonID, chrUrlArticle, chrVolume, intJournalCode) values ('Journal abstract example','yes','u1111111','020102','030303','040106','21','RO2','RL2','u1111111xPUB2','57-59','Test Journal Title','2013','1111111112','861503','970102','9111111112','http://google.com.au','12',1)

insert into Research_outputs_data_authors (chrOutputInvestigatorCode, chrOutput6Code, chrStaffNumber, chrRoute, chrOrder) values ('u1111111xPUB2xExternal','u1111111xPUB2', 'E1111','External','1');
insert into Research_outputs_data_authors (chrOutputInvestigatorCode, chrOutput6Code, chrStaffNumber, chrRoute, chrOrder) values ('u1111111xPUB2xu1111111', 'u1111111xPUB2','u1111111','Internal','2');
insert into Research_outputs_data_authors (chrOutputInvestigatorCode, chrOutput6Code, chrStaffNumber, chrRoute, chrOrder) values ('u1111111xPUB2xE1112','u1111111xPUB2', 'E1112','External','3');
insert into Research_outputs_data_authors (chrOutputInvestigatorCode, chrOutput6Code, chrStaffNumber, chrRoute, chrOrder) values ('u1111111xPUB2xu1111112', 'u1111111xPUB2','u1111112','Internal','4');

insert into Research_outputs_data_authors_departments (chrOutputDepartmentCode, chrDepartmentCode, chrOutputInvestigatorCode) values ('u1111111xPUB2xu1111111x1','1','u1111111xPUB2xu1111111');
insert into Research_outputs_data_authors_departments (chrOutputDepartmentCode, chrDepartmentCode, chrOutputInvestigatorCode) values ('u1111111xPUB2xu1111112x2','1','u1111111xPUB2xu1111112');

insert into Research_outputs_data_documents (chrOutputDocumentCode, chrOutput6Code, intOutputDocumentCounter, chrDocumentName, chrURL) values ('u1111111xPUB2xDOC1','u1111111xPUB2',1,'ANU Repository Link','http://hdl.handle.net/1885/2');

insert into Research_outputs_notes (chrOutput6Code, intOutputNotesCounter, chrOutputsNotesCode, intNotesType, chrNotes) values ('u1111111xPUB2',1,'u1111111xPUB2/1',5,'Copyright Elsevier');
insert into Research_outputs_notes (chrOutput6Code, intOutputNotesCounter, chrOutputsNotesCode, intNotesType, chrNotes) values ('u1111111xPUB2',2,'u1111111xPUB2/2',4,'Testing');

insert into Research_outputs_journals(intJournalCode, chrJournalName, chrISSN, intPublisherID) values (2,'Physical Review A: Atomic, Molecular and Optical Physics','1050-2947', 3);

insert into Research_outputs_data1(chrCreatedByCode,chrOutput1Code, chrOutput2Code, chrOutput6Code, chrReportingYear, chrPublicationTitle,chrSeriesTitle,chrUrlArticle,chrIssue,intJournalCode,chrVolume,chrFORcode1,chrScopusID) values ('u4169254','RO2','RL2','u4169254xPUB3','2006', 'Angular correlation in the two-electron continuum','020708-1-4','http://dx.doi.org/10.1103/PhysRevA.73.020708',2,2,'73','020201','2-s2.0-33644537369');

insert into Research_outputs_data_authors (chrOutputInvestigatorCode, chrOutput6Code, chrStaffNumber, chrRoute, chrOrder) values ('u4169254xPUB3xu1111111','u4169254xPUB3','u1111111','Internal','1');
insert into Research_outputs_data_authors (chrOutputInvestigatorCode, chrOutput6Code, chrStaffNumber, chrRoute, chrOrder) values ('u4169254xPUB3xE1113','u4169254xPUB3','E1113','External','2');

insert into Research_outputs_data_authors_departments (chrOutputDepartmentCode, chrDepartmentCode, chrOutputInvestigatorCode) values ('u4169254xPUB3xu1111111x1','1','u4169254xPUB3xu1111111');

insert into Research_outputs_notes (chrOutput6Code, intOutputNotesCounter, chrOutputsNotesCode, intNotesType, chrNotes) values ('u4169254xPUB3',1,'u4169254xPUB3/1',5,'Copyright American Physical Society');

insert into Research_outputs_data_documents (chrOutputDocumentCode, chrOutput6Code, intOutputDocumentCounter, chrDocumentName, chrURL) values ('u4169254xPUB3xDOC2','u4169254xPUB3',2,'ANU Repository Link','http://hdl.handle.net/10440/1001');

insert into Research_outputs_data1(chrApplicationDescription, chrCreatedByCode, chrFORcode1, chrOutput1Code, chrOutput2Code, chrOutput6Code, chrPublicationPlace, chrPublicationTitle, chrSEOncode1, chrTitleOfConferenceProceedings, dateFirstPerformed) values ('Map drawn sometime in history','u1111111','090901','RO12','RL28','u1111111xPUB4 ','Canberra, Australia','Mountains in Australia','960909','Atlas of the World','2005-02-11 00:00:00');

insert into Research_outputs_journals (intJournalCode, chrISSN, chrJournalName, intPublisherID) values (3,'1758-8103','Clinical Obesity',4);
insert into Research_outputs_data1(chrCreatedByCode,chrOutput1Code, chrOutput2Code, chrOutput6Code, chrReportingYear, chrPublicationTitle,chrUrlArticle,chrIssue,intJournalCode,chrVolume) values ('u1111111','RO2','RL2','u1111111xPUB5','2014', 'The effectiveness of including support people in a cognitive-behavioural weight loss maintenance program for obese adults: study rationale and design','http://onlinelibrary.wiley.com/doi/10.1111/cob.12042/abstract','2',3,'4');

insert into Research_outputs_journals (intJournalCode, chrISSN, chrJournalName, intPublisherID) values (4,'1440-6381','Australasian Journal on Ageing',4);
insert into Research_outputs_data1(chrCreatedByCode,chrOutput1Code, chrOutput2Code, chrOutput6Code, chrReportingYear, chrPublicationTitle,chrUrlArticle,chrIssue,intJournalCode,chrVolume) values ('u1111111','RO2','RL2','u1111111xPUB6','2013', 'Thirty years of the United Nations and global ageing : an Australian perspective','http://doi.org/10.1111/ajag.12101','2',4,'32');
--insert into Research_outputs_data1(chrCreatedByCode,chrOutput1Code, chrOutput2Code, chrOutput6Code, chrReportingYear, chrPublicationTitle,chrUrlArticle,chrIssue,intJournalCode,chrVolume) values ('u1111111','RO2','RL2','u1111111xPUB7','2013', 'Considering relocation to a retirement village: predictors from a community sample','http://doi.org/10.1111/j.1741-6612.2012.00618.x','2',4,'32');

insert into Research_outputs_data1(chrCreatedByCode,chrOutput1Code, chrOutput2Code, chrOutput6Code, chrReportingYear, chrPublicationTitle,chrUrlArticle,chrIssue,intJournalCode,chrVolume) values ('u1111111','RO2','RL2','u1111111xPUB7','2013', 'High "normal" blood glucose is associated with decreased brain volume and cognitive performance in the 60s: The PATH through life study','http://doi.org/10.1111/j.1741-6612.2012.00618.x','2',4,'32');

insert into Research_outputs_data_authors (chrOutputInvestigatorCode, chrOutput6Code, chrStaffNumber, chrRoute, chrOrder) values ('u1111111xPUB7xu1111114Internal','u1111111xPUB7','u1111114','Internal','1');
insert into Research_outputs_data_authors (chrOutputInvestigatorCode, chrOutput6Code, chrStaffNumber, chrRoute, chrOrder) values ('u1111111xPUB7xE1114External','u1111111xPUB7','E1114','External','2');
insert into Research_outputs_data_authors (chrOutputInvestigatorCode, chrOutput6Code, chrStaffNumber, chrRoute, chrOrder) values ('u1111111xPUB7xu1111115Internal','u1111111xPUB7','u1111115','Internal','3');
insert into Research_outputs_data_authors (chrOutputInvestigatorCode, chrOutput6Code, chrStaffNumber, chrRoute, chrOrder) values ('u1111111xPUB7xu1111116Internal','u1111111xPUB7','u1111116','Internal','4');
insert into Research_outputs_data_authors (chrOutputInvestigatorCode, chrOutput6Code, chrStaffNumber, chrRoute, chrOrder) values ('u1111111xPUB7xu1111117Internal','u1111111xPUB7','u1111117','Internal','5');


insert into Research_outputs_data_authors_departments (chrOutputDepartmentCode, chrDepartmentCode, chrOutputInvestigatorCode) values ('u1111111xPUB7xu1111114Internalx1','1','u1111111xPUB7xu1111114Internal');
insert into Research_outputs_data_authors_departments (chrOutputDepartmentCode, chrDepartmentCode, chrOutputInvestigatorCode) values ('u1111111xPUB7xu1111115Internalx1','1','u1111111xPUB7xu1111115Internal');
insert into Research_outputs_data_authors_departments (chrOutputDepartmentCode, chrDepartmentCode, chrOutputInvestigatorCode) values ('u1111111xPUB7xu1111116Internalx1','1','u1111111xPUB7xu1111116Internal');
insert into Research_outputs_data_authors_departments (chrOutputDepartmentCode, chrDepartmentCode, chrOutputInvestigatorCode) values ('u1111111xPUB7xu1111117Internalx1','1','u1111111xPUB7xu1111117Internal');

insert into Research_outputs_data1(chrApplicationDescription, chrCommissionedBy, chrCreatedByCode, chrISSN, chrOutput1Code, chrOutput2Code, chrOutput6Code, chrPageNumbers, chrPublicationPlace,chrPublicationTitle, chrReportingYear, intPublisherID) values ('Some description of the report','Australian Government','u1111111','1440-6381','RO10','RL31','u1111111xPUB8','1-80','Canberra, Australia','Test Report','2013', 4);
insert into Research_outputs_data_authors (chrOutputInvestigatorCode, chrOutput6Code, chrStaffNumber, chrRoute, chrOrder) values ('u1111111xPUB8xu1111111Internal','u1111111xPUB8','u1111117','Internal','1');

insert into Research_outputs_data_authors_departments (chrOutputDepartmentCode, chrDepartmentCode, chrOutputInvestigatorCode) values ('u1111111xPUB8xu1111111Internalx1','1','u1111111xPUB8xu1111111Internal');
