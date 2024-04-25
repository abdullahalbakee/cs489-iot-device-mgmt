import React from "react";
import { addProduct } from "../../service/products";
import { useNavigate } from "react-router-dom";
import AddEditForm from "./add-edit-form";
import Product from "../../models/product";
import { useToast } from "react-toastify";

export default function () {
  const alert = useToast();
  const navigate = useNavigate();
  const handleAdd = async (product) => {
    const res = await addProduct(product);
    if (res.success) {
      alert.success("Product added successfully!");
      navigate("/my-products");
    } else {
      alert.error("Product could not be added." + res.error);
    }
  };

  return (
    <AddEditForm
    initialProduct={new Product()}
      formTitle="Add a new product"
      actionText="Add Product"
      onAction={handleAdd}
    />
  );
}
