import axios from "./common";
import { getToken } from "./token";

export async function getMyProducts() {
  try {
    return [];
    const response = await axios.get("/my-products", {
      headers: { Authorization: `Bearer ${getToken()}` },
    });
    return response.data;
  } catch (error) {
    console.log("getMyProducts", error);
    return error.data;
  }
}

export async function deleteMyProductById(productId) {
  try {
    return [];
    const response = await axios.delete("/my-products/" + productId, {
      headers: { Authorization: `Bearer ${getToken()}` },
    });
    return response.data;
  } catch (error) {
    console.log("getMyProducts", error);
    return error.data;
  }
}

export async function updateProduct(product) {
  try {
    return [];
    const response = await axios.put("/my-products/" + product.id, product, {
      headers: { Authorization: `Bearer ${getToken()}` },
    });
    return response.data;
  } catch (error) {
    console.log("getMyProducts", error);
    return error.data;
  }
}
