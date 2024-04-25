import axios from "./common";
import { getToken } from "./token";

export async function getHistory() {
  try {
    const response = await axios.get("/history", {
      headers: { Authorization: `Bearer ${getToken()}` },
    });
    return response.data;
  } catch (error) {
    console.log("getHistory", error);
    return error.data;
  }
}
