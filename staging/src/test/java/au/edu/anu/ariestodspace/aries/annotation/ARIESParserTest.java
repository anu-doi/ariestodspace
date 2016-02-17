package au.edu.anu.ariestodspace.aries.annotation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.ariestodspace.aries.ARIESPersistenceManager;
import au.edu.anu.ariestodspace.aries.ExternalAuthor;
import au.edu.anu.ariestodspace.aries.ForCodes;
import au.edu.anu.ariestodspace.aries.InternalAuthor;
import au.edu.anu.ariestodspace.aries.ResearchOutputsData1;
import au.edu.anu.ariestodspace.aries.ResearchOutputsDataAuthors;
import au.edu.anu.ariestodspace.aries.ResearchOutputsDataDocuments;
import au.edu.anu.ariestodspace.aries.ResearchOutputsJournals;
import au.edu.anu.ariestodspace.aries.ResearchOutputsJournalsPublishers;
import au.edu.anu.ariestodspace.aries.ResearchOutputsNotes;
import au.edu.anu.ariestodspace.aries.SeoCodes;
import au.edu.anu.ariestodspace.aries.annotation.ARIESParser;
import au.edu.anu.ariestodspace.aries.documents.ANURepositoryLink;
import au.edu.anu.ariestodspace.aries.notes.CopyrightInformation;
import au.edu.anu.ariestodspace.aries.notes.Keywords;
import au.edu.anu.ariestodspace.aries.outputs.JournalArticle;

public class ARIESParserTest {
	Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	private static EntityManagerFactory emf;

	@BeforeClass
	public static void before() {
		emf = ARIESPersistenceManager.getInstance().getEntityManagerFactory();
	}
	
	@AfterClass
	public static void after() {
		ARIESPersistenceManager.getInstance().closeEntityManagerFactory();
	}
	
	@Test
	public void testResearchOutput() {
		EntityManager em = emf.createEntityManager();

		Query query = em.createQuery("from ResearchOutputsData1 WHERE chrOutput6Code = 'u1111111xPUB2'");
		ResearchOutputsData1 data1 = (ResearchOutputsData1)query.getSingleResult();
		em.close();
		assertNotNull("Data 1 is null", data1);
		if (!(data1 instanceof JournalArticle)) {
			fail("Unexpected type for research output, expected JournalArticle");
		}
		JournalArticle journalArticle = (JournalArticle) data1;
		assertEquals("Unexpected aries identifier", "u1111111xPUB2", journalArticle.getChrOutput6Code());
		assertEquals("Unexpected abstract", "Journal abstract example", journalArticle.getChrApplicationDescription());
		assertEquals("Unexpected referreed status", "yes", journalArticle.getChrConferencePaperRefereed());
		assertEquals("Unexpected created by code", "u1111111", journalArticle.getChrCreatedByCode());
		
		ForCodes forcode1 = journalArticle.getChrFORcode1();
		
		assertNotNull("No value for FOR Code 1", forcode1);
		assertEquals("Unexpected field of research division code", "02", forcode1.getChrForDivisionCode());
		assertEquals("Unexpected field of research group code", "0201", forcode1.getChrForGroupCode());
		assertEquals("Unexpected field of research objective code", "020102", forcode1.getChrForObjectiveCode());
		assertEquals("Unepxected field of research description", "Astronomical and Space Instrumentation", forcode1.getChrForDescription());
		assertEquals("Unexpected For Code 1 value", "020102 - Astronomical and Space Instrumentation", forcode1.getValue());

		ForCodes forcode2 = journalArticle.getChrFORcode2();
		assertNotNull("No value for FOR Code 1", forcode2);
		assertEquals("Unexpected For Code 2 value", "030303 - Optical Properties of Materials", forcode2.getValue());
		
		ForCodes forcode3 = journalArticle.getChrFORcode3();
		assertNotNull("No value for FOR Code 1", forcode3);
		assertEquals("Unexpected For Code 3 value", "040106 - Cloud Physics", forcode3.getValue());
		
		assertEquals("Unexpected issue number", "21", journalArticle.getChrIssue());
		assertEquals("Unexpected output 1 code", "RO2", journalArticle.getChrOutput1Code());
		assertEquals("Unexpected output 2 code", "RL2", journalArticle.getChrOutput2Code());
		assertEquals("Unexpected page numbers", "57-59", journalArticle.getChrPageNumbers());
		assertEquals("Unepxected publication title", "Test Journal Title", journalArticle.getChrPublicationTitle());
		assertEquals("Unepxected reporting (publication) year", "2013", journalArticle.getChrReportingYear());
		assertEquals("Unexpected scopus id", "1111111112", journalArticle.getChrScopusID());
		
		SeoCodes seocodes1 = journalArticle.getChrSEOncode1();
		assertNotNull("No value for SEO Code 1", seocodes1);
		assertEquals("Unexpected SEO subdivision code", "86", seocodes1.getChrSubdivisionCode());
		assertEquals("Unexpected SEO group code", "8615", seocodes1.getChrGroupCode());
		assertEquals("Unexpected SEO class code", "861503", seocodes1.getChrClassCode());
		assertEquals("Scientific Instruments", seocodes1.getChrSubdivision());
		assertEquals("Unexpected SEO Code 1 value", "861503 - Scientific Instruments", seocodes1.getValue());
		
		SeoCodes seocodes2 = journalArticle.getChrSEOncode2();
		assertNotNull("No value for SEO Code 2", seocodes2);
		assertEquals("Unexpected SEO Code 2 value", "970102 - Expanding Knowledge in the Physical Sciences", seocodes2.getValue());
		
		assertNull("Value found for SEO code 3", journalArticle.getChrSEOncode3());
		
		assertEquals("Unexpected thompson id", "9111111112", journalArticle.getChrThompsonID());
		assertEquals("Unexpected url", "http://google.com.au", journalArticle.getChrUrlArticle());
		assertEquals("Unexpected volume number", "12", journalArticle.getChrVolume());
		
		ResearchOutputsJournals journal = journalArticle.getJournal();
		assertNotNull("Expected a journal", journal);
		
		assertEquals("Unexpected journal code", new Integer(1),journal.getIntJournalCode());
		assertEquals("Unexpected ISSN","1234-5678", journal.getChrISSN());
		assertEquals("Unexpected journal name", "Journal of all", journal.getChrJournalName());
		
		ResearchOutputsJournalsPublishers publisher = journal.getPublisher();
		assertNotNull("Expected a publisher", publisher);
		
		assertEquals("Unexpected publisher id",new Integer(1), publisher.getIntPublisherID());
		assertEquals("Unexpected publisher name", "Elsevier", publisher.getChrPublisher());
		
		assertNotNull("No notes found", journalArticle.getNotes());
		assertEquals("Unexpected number of notes", 2, journalArticle.getNotes().size());
		ResearchOutputsNotes copyrightNotes = data1.getNotes().get(0);
		if (!(copyrightNotes instanceof CopyrightInformation)) {
			fail("Unexpected type for notes, expected CopyrightInformation");
		}
		assertEquals("Unexpected output notes code for copyright notes","u1111111xPUB2/1", copyrightNotes.getChrOutputsNotesCode());
		assertEquals("Unexpected output notes counter for copyright notes", new Integer(1), copyrightNotes.getIntOutputNotesCounter());
		assertEquals("Unexpected notes type for copyright notes", new Integer(5), copyrightNotes.getIntNotesType());
		assertEquals("Unexpected notes value for copyright notes", "Copyright Elsevier", copyrightNotes.getChrNotes());
		ResearchOutputsNotes keywordsNotes = journalArticle.getNotes().get(1);
		if (!(keywordsNotes instanceof Keywords)) {
			fail("Unexpected type for notes, expected Keywords");
		}
		assertEquals("Unexpected output notes code for keywords notes","u1111111xPUB2/2", keywordsNotes.getChrOutputsNotesCode());
		assertEquals("Unexpected output notes counter for keywords notes", new Integer(2), keywordsNotes.getIntOutputNotesCounter());
		assertEquals("Unexpected notes type for keywords notes", new Integer(4), keywordsNotes.getIntNotesType());
		assertEquals("Unexpected notes value for keywords notes", "Testing", keywordsNotes.getChrNotes());
		
		List<ResearchOutputsDataDocuments> documents = journalArticle.getDocuments();
		assertNotNull("No documents found", documents);
		assertEquals("Unexpected number of documents", 1, documents.size());
		
		ResearchOutputsDataDocuments document = documents.get(0);
		if(!(document instanceof ANURepositoryLink)) {
			fail("Unexpected document type, expected ANURepositoryLink");
		}
		ANURepositoryLink link = (ANURepositoryLink) document;
		assertEquals("Unexpected document code", "u1111111xPUB2xDOC1", link.getChrOutputDocumentCode());
		assertEquals("Unexpected document counter",new Integer(1), link.getIntOutputDocumentCounter());
		assertEquals("Unexpected document name", "ANU Repository Link", link.getChrDocumentName());
		assertEquals("Unexpected url", "http://hdl.handle.net/1885/2", link.getChrURL());
		
		List<ResearchOutputsDataAuthors> authors = journalArticle.getAuthors();
		assertNotNull("No authors found", authors);
		assertEquals("Unexpected number of authors", 4, authors.size());

		ResearchOutputsDataAuthors firstAuthor = authors.get(0);
		if (!(firstAuthor instanceof ExternalAuthor)) {
			fail("Unexpected type of author, expected ExternalAuthor");
		}
		ExternalAuthor author1 = (ExternalAuthor) firstAuthor;
		assertEquals("Unexpected author name","Body, Some",author1.getName());
		assertEquals("Unexpected affiliation","Body, Some, University of New South Wales",author1.getAffiliation());
		assertNull("Unexpected university id", author1.getUid());
		
		ResearchOutputsDataAuthors secondAuthor = authors.get(1);
		if (!(secondAuthor instanceof InternalAuthor)) {
			fail("Unexpected type of author, expected InternalAuthor");
		}
		InternalAuthor author2 = (InternalAuthor) secondAuthor;
		assertEquals("Unexpected author name","Turner, Genevieve",author2.getName());
		assertEquals("Unexpected affiliation","Turner, Genevieve, Information Technology Services, The Australian National University",author2.getAffiliation());
		assertEquals("Unexpected university id","u1111111",author2.getUid());
	}
	
	@Test
	public void testTransformedARIESOjbect() {
		EntityManager em = emf.createEntityManager();

		Query query = em.createQuery("from ResearchOutputsData1 WHERE chrOutput6Code = 'u1111111xPUB2'");
		ResearchOutputsData1 data1 = (ResearchOutputsData1)query.getSingleResult();
		em.close();
		
		ARIESParser parser = new ARIESParser();
		try {
			Map<String, List<String>> ariesMap = parser.getDSpaceValues(data1);
			assertNotNull("ARIES Map unexpectedly null",ariesMap);
			assertEquals("Unexpected number of elements in the map", 26, ariesMap.size());
			List<String> subjects = ariesMap.get("dc.subject");
			assertNotNull("No subjects found", subjects);
			assertEquals("Unexpected number of subjects", 1, subjects.size());
			assertEquals("Unexpected subject value", "Testing", subjects.get(0));
			
			List<String> abstractList = ariesMap.get("dc.description.abstract");
			assertNotNull("No abstracts found", abstractList);
			assertEquals("Unexpected number of abstracts", 1, abstractList.size());
			assertEquals("Unexpected abstract value", "Journal abstract example", abstractList.get(0));
			
			List<String> sources = ariesMap.get("dc.source");
			assertNotNull("No sources found", sources);
			assertEquals("Unexpected number of sources", 1, sources.size());
			assertEquals("Unexpected source value","Journal of all", sources.get(0));
			
			List<String> lastpages = ariesMap.get("local.bibliographicCitation.lastpage");
			assertNotNull("No last page found", lastpages);
			assertEquals("Unepxected number of last pages", 1, lastpages.size());
			assertEquals("Unexpected last page value", "59", lastpages.get(0));
			
			List<String> publishers = ariesMap.get("dc.publisher");
			assertNotNull("No publishers found", publishers);
			assertEquals("Unexpected publishers", 1, publishers.size());
			assertEquals("Unexpected publisher value", "Elsevier", publishers.get(0));
			
			List<String> rights = ariesMap.get("dc.rights");
			assertNotNull("No rights found", rights);
			assertEquals("Unexpected number of rights values", 1, rights.size());
			assertEquals("Unexpected rights value", "Copyright Elsevier", rights.get(0));
			
			List<String> authors = ariesMap.get("dc.contributor.author");
			assertNotNull("No authors found", authors);
			assertEquals("Unexpected number of authors", 4, authors.size());
			assertEquals("Unexpected author value", "Body, Some", authors.get(0));
			assertEquals("Unexpected author value", "Turner, Genevieve", authors.get(1));
			assertEquals("Unexpected author value", "Person, Random", authors.get(2));
			assertEquals("Unexpected author value", "Khanna, Rahul", authors.get(3));

			List<String> objectives = ariesMap.get("local.identifier.absseo");
			assertNotNull("No socio-economic objectives found", objectives);
			assertEquals("Unexpected number of socio-economic objectives", 2, objectives.size());
			assertEquals("Unexpected socio-economic objective", "861503 - Scientific Instruments", objectives.get(0));
			assertEquals("Unexpected socio-economic objective", "970102 - Expanding Knowledge in the Physical Sciences", objectives.get(1));
			
			List<String> types = ariesMap.get("dc.type");
			assertNotNull("No types found", types);
			assertEquals("Unexpected number of types",1, types.size());
			assertEquals("Unexpected type value", "Journal article", types.get(0));
			
			List<String> startpages = ariesMap.get("local.bibliographicCitation.startpage");
			assertNotNull("No start pages found", startpages);
			assertEquals("Unexpected number of start pages", 1, startpages.size());
			assertEquals("Unexpected start page value", "57", startpages.get(0));
			
			List<String> fields = ariesMap.get("local.identifier.absfor");
			assertNotNull("No fields of research found", fields);
			assertEquals("Unexpected number of fields of research", 3, fields.size());
			Collections.sort(fields);
			assertEquals("Unexpected field of research", "020102 - Astronomical and Space Instrumentation",fields.get(0));
			assertEquals("Unexpected field of research", "030303 - Optical Properties of Materials",fields.get(1));
			assertEquals("Unexpected field of research", "040106 - Cloud Physics",fields.get(2));
			
			List<String> uris = ariesMap.get("dc.identifier.uri");
			assertNotNull("No URI's found", uris);
			assertEquals("Unexpected number of URI's",1,uris.size());
			assertEquals("Unexpected URI value", "http://hdl.handle.net/1885/2", uris.get(0));
			
			List<String> submittedBy = ariesMap.get("local.identifier.uidSubmittedBy");
			assertNotNull("No submitted by found", submittedBy);
			assertEquals("Unexpected number of submitted bys", 1, submittedBy.size());
			assertEquals("Unexpected submitted by value","u1111111", submittedBy.get(0));
			
			List<String> sourceUris = ariesMap.get("dc.source.uri");
			assertNotNull("No source uri's found", sourceUris);
			assertEquals("Unexpected number of source URi's", 1, sourceUris.size());
			assertEquals("Unexpected source URI value","http://google.com.au", sourceUris.get(0));
			
			List<String> authoruids = ariesMap.get("local.contributor.authoruid");
			assertNotNull("No author uids found", authoruids);
			assertEquals("Unexpected number of author uids", 2, authoruids.size());
			assertEquals("Unexpected author uid", "Turner, Genevieve, u1111111", authoruids.get(0));
			assertEquals("Unexpected author uid", "Khanna, Rahul, u1111112", authoruids.get(1));
			
			List<String> issueNumbers = ariesMap.get("local.bibliographicCitation.issue");
			assertNotNull("No issue numbers found", issueNumbers);
			assertEquals("Unexpected number of issue numbers", 1, issueNumbers.size());
			assertEquals("Unexpected issue number", "21", issueNumbers.get(0));
			
			List<String> refereed = ariesMap.get("local.description.refereed");
			assertNotNull("No refereed found", refereed);
			assertEquals("Unexpected number of refereed values", 1, refereed.size());
			assertEquals("Unexpected refereed value","yes",refereed.get(0));
			
			List<String> titles = ariesMap.get("dc.title");
			assertNotNull("No titles found", titles);
			assertEquals("Unexpected number of titles", 1, titles.size());
			assertEquals("Unexpected title value", "Test Journal Title", titles.get(0));
			
			List<String> affiliations = ariesMap.get("local.contributor.affiliation");
			assertNotNull("No affiliations found", affiliations);
			assertEquals("Unexpected number of affiliations", 4, affiliations.size());
			assertEquals("Unexpected affiliation value", "Body, Some, University of New South Wales", affiliations.get(0));
			assertEquals("Unexpected affiliation value","Turner, Genevieve, Information Technology Services, The Australian National University",affiliations.get(1));
			assertEquals("Unexpected affiliation value","Person, Random, Sydney University",affiliations.get(2));
			assertEquals("Unexpected affiliation value","Khanna, Rahul, Information Technology Services, The Australian National University",affiliations.get(3));
			
			List<String> ariesPublications = ariesMap.get("local.identifier.ariespublication");
			assertNotNull("No aries publications found", ariesPublications);
			assertEquals("Unexpected number of aries publications", 1, ariesPublications.size());
			assertEquals("Unexpected aries publication value", "u1111111xPUB2", ariesPublications.get(0));
			
			List<String> volumes = ariesMap.get("local.identifier.citationvolume");
			assertNotNull("No volumes found", volumes);
			assertEquals("Unexpected number of volumes", 1, volumes.size());
			assertEquals("Unexpected volume value", "12", volumes.get(0));
			
			List<String> issuedDates = ariesMap.get("dc.date.issued");
			assertNotNull("No issued date found", issuedDates);
			assertEquals("unexpected number of issued dates", 1, issuedDates.size());
			assertEquals("Unexpected issued date","2013", issuedDates.get(0));
			
			List<String> issns = ariesMap.get("dc.identifier.issn");
			assertNotNull("No issn found", issns);
			assertEquals("Unexpected number of issns", 1, issns.size());
			assertEquals("Unexpected issn value", "1234-5678", issns.get(0));
		}
		catch (IllegalAccessException | InvocationTargetException e) {
			fail("Error parser ARIES record");
		}
	}
}
