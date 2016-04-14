package com.noklin.fastentask.data.beans;

import com.noklin.fastentask.data.entities.CdEntity;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

@Stateless(name = "DataManagerEJB")
public class DataManagerBean {
    @EJB
    private CdDaoBean cdDao;
    @EJB
    private XmlCdParserBean parses;

    public DataManagerBean() {
    }

    public void addOrUpdate(CdEntity entity){
        cdDao.addOrUpdate(entity);
    }

    public void addOrUpdateDcFromInputStream(InputStream is) throws ParserConfigurationException
            , SAXException, IOException {

        List<CdEntity> list  = parses.listFromInputStream(is);
        for(CdEntity entity : list)
            cdDao.addOrUpdate(entity);
    }

    public List<CdEntity> getCdList(){
        return cdDao.getAllCds();
    }

    public CdEntity findCd(String id){
        return cdDao.find(id);
    }

    public void writeCdsToStream(OutputStream out) throws TransformerException
            , ParserConfigurationException {

        parses.uploadCdEntities(cdDao.getAllCds() , out);
    }
}

