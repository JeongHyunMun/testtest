import axios from 'axios'

axios.defaults.baseURL = '/hyeon'

axios.interceptors.request.use(
    function(config) {
         if(config.method == 'get') {        
        config.headers['Accept'] = 'application/json, text/plain, */*';
      }
      else if(config.method == 'post') {        
        config.headers['Accept'] = 'application/json, text/plain, */*';
        config.headers['Content-Type'] = 'application/x-www-form-urlencoded';
        config.headers['withCredentials'] = true;
      }
      return config;
    },
);

export default axios;