import axios from "./common";
import { getToken } from "./token";

export async function addProduct(product) {
  try {
    return [];
    const response = await axios.post("/products", product, {
      headers: { Authorization: `Bearer ${getToken()}` },
    });
    return response.data;
  } catch (error) {
    console.log("addProduct", error);
    return error.data;
  }
}

export async function getProducts(query) {
  let params = {};
  if (query && query.length > 0) {
    params = { title: query };
  }
  try {
    return [];
    const response = await axios.get("/products", {
      headers: { Authorization: `Bearer ${getToken()}` },
      params: params,
    });
    return response.data;
  } catch (error) {
    console.log("getProducts", error);
    return error.data;
  }
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
