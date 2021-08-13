import axios from 'axios';

export default {
    createSession(session) {
        return axios.post('/session', session);
    }
}