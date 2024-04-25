import axios from "./common";

export async function register(user) {
  try {
    return [];
    const response = await axios.post("/auth/register", user);
    return response.data;
  } catch (error) {
    console.log("register", error);
    return error.data;
  }
}

export async function login(user) {
    const response = await axios.post("/login", user);
    return response.data;
}
