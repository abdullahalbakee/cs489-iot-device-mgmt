import React, { useEffect, useState } from "react";
import Button from "react-bootstrap/button";
import Form from "react-bootstrap/Form";
import Product from "../../models/product";
import Container from "react-bootstrap/esm/Container";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";

export default function ({ initialProduct, formTitle, actionText, onAction }) {
  const [product, setProduct] = useState(new Product());
  const [dueDate, setDueDate] = useState(new Date());

  useEffect(() => {
    console.log("Setting initial product!", initialProduct);
    setProduct({ ...initialProduct });
  }, [initialProduct]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    product.dueDate = dueDate;
    product.dueDate.setHours(0, 0, 0, 0);
    onAction(product);
  };

  const handleTextChange = (e) => {
    setProduct({ ...product, [e.target.name]: e.target.value });
  };

  const handleCheckBox = (e) => {
    setProduct({ ...product, [e.target.name]: e.target.checked });
  };

  return (
    <Container className="mt-5">
      <h3 className="text-center">{formTitle}</h3>
      <hr />
      <Form
        className="col-12 col-sm-6 col-md-3 mx-auto"
        onSubmit={handleSubmit}
      >
        <Form.Group className="mb-3" controlId="productTitle">
          <Form.Label>Title</Form.Label>
          <Form.Control
            type="text"
            placeholder="Title"
            name="title"
            onChange={handleTextChange}
            value={product.title}
            required
          />
        </Form.Group>

        <Form.Group className="mb-3" controlId="productDescription">
          <Form.Label>Description</Form.Label>
          <Form.Control
            as="textarea"
            rows={3}
            placeholder="Description"
            name="description"
            onChange={handleTextChange}
            value={product.description}
            required
          />
        </Form.Group>
        <Form.Group className="mb-3" controlId="productPrice">
          <Form.Label>Price</Form.Label>
          <Form.Control
            type="number"
            placeholder="Price"
            name="price"
            onChange={handleTextChange}
            value={product.price}
            required
          />
        </Form.Group>
        <Form.Group className="mb-3" controlId="productDueDate">
          <Form.Label>Due Date</Form.Label>
          <DatePicker
            showIcon
            selected={dueDate}
            onChange={setDueDate}
            value={product.dueDate}
          />
        </Form.Group>
        <Form.Group className="mb-3" controlId="productRelease">
          <Form.Check
            type="checkbox"
            label="Save & Release"
            name="released"
            onChange={handleCheckBox}
            selected={product.released}
          />
        </Form.Group>
        <Button variant="primary" type="submit">
          {actionText}
        </Button>
      </Form>
    </Container>
  );
}
