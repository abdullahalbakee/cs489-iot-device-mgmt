import React, { useEffect, useState } from "react";
import { getProduct } from "../../service/products";
import { useNavigate, useParams } from "react-router-dom";
import AddEditForm from "./add-edit-form";
import Product from "../../models/product";
import { updateProduct } from "../../service/my-products";
import { useToast } from "react-toastify";

export default function () {
  const alert = useToast();
  const params = useParams();
  const productId = params.productId;
  const navigate = useNavigate();
  const [product, setProduct] = useState(new Product());

  const getSingleProduct = async () => {
    const res = await getProduct(productId);
    if (res.success) {
      setProduct(res.data);
    } else {
      alert.error(res.error);
    }
  };

  useEffect(() => {
    getSingleProduct();
  }, []);

  const handleEdit = async (product) => {
    const res = await updateProduct(product);
    if (res.success) {
      alert.success("Product updated successfully!");
      navigate("/my-products");
    } else {
      alert.error("Product could not be updated." + res.error);
    }
  };

  return (
    <AddEditForm
      initialProduct={product}
      formTitle="Edit product"
      actionText="Update"
      onAction={handleEdit}
    />
  );
}
