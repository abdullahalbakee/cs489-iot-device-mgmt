import axios from "axios";

export const setBearerToken = (token) => {
  axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
};

export const removeBearerToken = () => {
  axios.defaults.headers.common["Authorization"] = undefined;
};

axios.defaults.baseURL = process.env.REACT_APP_BASE_URL;
export default axios;
