package com.jobTracker.JobTrackerApplication.GeminiAi;

public class PromptConstants {

    public static final String RESUME_PROMPT =
            "Extract and organize the following information from this resume text:\n\n" +
                    "1. Contact Information: First Name, Last Name, Email, Phone Number, LinkedIn URL, GitHub URL, Address, City, State, Website URL\n" +
                    "2. Education: School, Location, GPA, Degree, Field of Study, Start Date, End Date, Description\n" +
                    "3. Work Experience: Company Name, Description, Position, Location, Type, Start Date, End Date, Current Position\n" +
                    "4. Certification: Certification Name, Certification Provider, Certification Link, Certification Validity Start, Certification Validity End\n" +
                    "5. Awards and Scholarships: Award Name, Organization, Award Date\n" +
                    "6. Projects: Project Name, Organization, Start Date, End Date, Additional Information\n" +
                    "7. Volunteering and Leadership: Organization, Involvement, City, State, Start Date, End Date, Additional Information\n" +
                    "8. Publication: Publication Name, Publisher, Date\n" +
                    "9. Professional Summary\n" +
                    "10. Target Titles\n" +
                    "11. Skills\n" +
                    "12. Interests\n" +
                    "13. Keywords Present\n\n" +
                    "Return the data in the following JSON schema, ensuring all list fields are arrays even if they contain a single entry:\n\n" +
                    "{\n" +
                    "    \"contactInformation\": {\n" +
                    "        \"firstName\": \"str\",\n" +
                    "        \"lastName\": \"str\",\n" +
                    "        \"email\": \"str\",\n" +
                    "        \"phoneNumber\": \"str\",\n" +
                    "        \"linkedinUrl\": \"str\",\n" +
                    "        \"githubUrl\": \"str\",\n" +
                    "        \"address\": \"str\",\n" +
                    "        \"city\": \"str\",\n" +
                    "        \"state\": \"str\",\n" +
                    "        \"websiteUrl\": \"str\"\n" +
                    "    },\n" +
                    "    \"Education\": [\n" +
                    "        {\n" +
                    "            \"school\": \"str\",\n" +
                    "            \"location\": \"str\",\n" +
                    "            \"gpa\": \"str\",\n" +
                    "            \"degree\": \"str\",\n" +
                    "            \"fieldOfStudy\": \"str\",\n" +
                    "            \"startDate\": \"str\",\n" +
                    "            \"endDate\": \"str\",\n" +
                    "            \"description\": \"str\"\n" +
                    "        }\n" +
                    "    ],\n" +
                    "    \"WorkExperience\": [\n" +
                    "        {\n" +
                    "            \"companyName\": \"str\",\n" +
                    "            \"description\": \"str\",\n" +
                    "            \"position\": \"str\",\n" +
                    "            \"location\": \"str\",\n" +
                    "            \"type\": \"str\",\n" +
                    "            \"startDate\": \"str\",\n" +
                    "            \"endDate\": \"str\",\n" +
                    "            \"currentPosition\": \"str\"\n" +
                    "        }\n" +
                    "    ],\n" +
                    "    \"Certification\": [\n" +
                    "        {\n" +
                    "            \"certificationName\": \"str\",\n" +
                    "            \"certificationProvider\": \"str\",\n" +
                    "            \"certificationLink\": \"str\",\n" +
                    "            \"certificationValidityStart\": \"str\",\n" +
                    "            \"certificationValidityEnd\": \"str\"\n" +
                    "        }\n" +
                    "    ],\n" +
                    "    \"AwardsAndScholarship\": [\n" +
                    "        {\n" +
                    "            \"awardName\": \"str\",\n" +
                    "            \"organisation\": \"str\",\n" +
                    "            \"awardDate\": \"str\"\n" +
                    "        }\n" +
                    "    ],\n" +
                    "    \"Project\": [\n" +
                    "        {\n" +
                    "            \"projectName\": \"str\",\n" +
                    "            \"organisation\": \"str\",\n" +
                    "            \"startDate\": \"str\",\n" +
                    "            \"endDate\": \"str\",\n" +
                    "            \"AdditionalInformation\": \"str\"\n" +
                    "        }\n" +
                    "    ],\n" +
                    "    \"VolunteeringAndLeadership\": [\n" +
                    "        {\n" +
                    "            \"organisation\": \"str\",\n" +
                    "            \"involvement\": \"str\",\n" +
                    "            \"city\": \"str\",\n" +
                    "            \"state\": \"str\",\n" +
                    "            \"startDate\": \"str\",\n" +
                    "            \"endDate\": \"str\",\n" +
                    "            \"additionalInformation\": \"str\"\n" +
                    "        }\n" +
                    "    ],\n" +
                    "    \"Publication\": [\n" +
                    "        {\n" +
                    "            \"publicationName\": \"str\",\n" +
                    "            \"publisher\": \"str\",\n" +
                    "            \"date\": \"str\"\n" +
                    "        }\n" +
                    "    ],\n" +
                    "    \"ProfessionalSummary\": \"str\",\n" +
                    "    \"targetTitles\": [\"str\"],\n" +
                    "    \"skills\": [\"str\"],\n" +
                    "    \"interests\": [\"str\"],\n" +
                    "    \"keyWordsPresent\": [\"str\"],\n" +
                    "    \"detectedRoles\": [\"str\"],\n" +
                    "}\n\n" +
                    "Additionally, detect the most relevant job roles based on the resume content and provide a list of keywords specific to that roles.\n\n" +
                    "Here is the resume text:\n\n";
}
