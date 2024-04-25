import axios from "./common";
import { getToken } from "./token";

export async function addClient(client) {
  try {
    const response = await axios.post("/clients", product, {
      headers: { Authorization: `Bearer ${getToken()}` },
    });
    return response.data;
  } catch (error) {
    console.log("addProduct", error);
    return error.data;
  }
}

export async function getClients(query) {
    const response = await axios.get("/clients", {
      headers: { Authorization: `Bearer ${getToken()}` }
    });
    return response.data;
}

export async function getProduct(productId) {
  try {
    return [];
    const response = await axios.get("/products/" + productId, {
      headers: { Authorization: `Bearer ${getToken()}` },
    });
    return response.data;
  } catch (error) {
    console.log("getProduct", error);
    return error.data;
  }
}

export async function getBids(productId) {
  try {
    return [];
    const response = await axios.get("products/" + productId + "/bids", {
      headers: { Authorization: `Bearer ${getToken()}` },
    });
    return response.data;
  } catch (error) {
    console.log("getBids", error);
    return error.data;
  }
}

export async function bidForProduct(productId, amount) {
  try {
    return [];
    const response = await axios.post(
      "products/" + productId + "/bids",
      { amount },
      {
        headers: { Authorization: `Bearer ${getToken()}` },
      }
    );
    return response.data;
  } catch (error) {
    console.log("getBids", error);
    return error.data;
  }
}
