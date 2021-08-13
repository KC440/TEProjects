package com.techelevator.service;

import com.techelevator.dao.SessionDao;
import com.techelevator.model.Session;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

        private SessionDao sessionDao;

        public SessionService (SessionDao sessionDao) {
            this.sessionDao = sessionDao;
        }

    public boolean  createSession( Session session)  {
        sessionDao.createSession(session);
        return true;
    }

}
