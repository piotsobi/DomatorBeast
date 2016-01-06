package com.demon.domatorbeast.logic;

/**
 * Created by Piotr on 2016-01-04.
 */
public class QueryBuilder {

    /**
     * Specify your database name here
     * @return
     */
    public String getDatabaseName() {
        return "itraining";
    }

    /**
     * Specify your MongoLab API here
     * @return
     */
    public String getApiKey() {
        return "ugOh8Htb3jZiUFDhwTCcqIpLT5s-gsiv";
    }

    /**
     * This constructs the URL that allows you to manage your database,
     * collections and documents
     * @return
     */
    public String getBaseUrl()
    {
        return "https://api.mongolab.com/api/1/databases/"+getDatabaseName()+"/collections/";
    }

    /**
     * Completes the formating of your URL and adds your API key at the end
     * @return
     */
    public String docApiKeyUrl()
    {
        return "?apiKey="+getApiKey();
    }

    /**
     * Returns the docs101 collection
     * @return
     */
    public String documentRequest()
    {
        return "training";
    }

    /**
     * Builds a complete URL using the methods specified above
     * @return
     */
    public String buildContactsSaveURL()
    {
        return getBaseUrl()+documentRequest()+docApiKeyUrl();
    }

    /**
     * Formats the contact details for MongoHQ Posting
     * @param contact: Details of the person
     * @return
     */
    /**
     * Formats the contact details for MongoHLab Posting
     * @param contact: Details of the person
     * @return
     */
    public String createContact(ExData contact)
    {
        return String
                .format("{\"opis\": \"%s\", "
                                + "\"level\": \"%s\", \"steps\": \"%s\"}",
                        contact.opis, contact.level, contact.steps);
    }

    public String buildContactsGetURL() {
        return getBaseUrl()+documentRequest()+docApiKeyUrl();
    }

    public String setContactData(ExData contact) {
        return String.format("{ \"$set\" : "
                        + "{\"opis\" : \"%s\", "
                        + "\"level\" : \"%s\", "
                        + "\"steps\" : \"%s\"}" + "}",
                contact.getOpis(),
                contact.getLevel(), contact.getSteps());
    }
}