import axios from "./common";

export async function register(user) {
  try {
    const response = await axios.post("/auth/register", user);
    return response.data;
  } catch (error) {
    console.log("register", error);
    return error.data;
  }
}

export async function login(user) {
  try {
    const response = await axios.post("/auth/login", user);
    return response.data;
  } catch (error) {
    console.log("login", error);
    return null;
  }
}