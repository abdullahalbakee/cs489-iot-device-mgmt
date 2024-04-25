import Container from "react-bootstrap/Container";
import Button from "react-bootstrap/Button";
import Table from "react-bootstrap/Table";
import Moment from "react-moment";
import { Link, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { deleteMyProductById, getMyProducts } from "../../service/my-products";
import { useToast } from "react-toastify";

export default function () {
  const alert = useToast();
  const [products, setProducts] = useState([]);
  const navigate = useNavigate();

  const getMyProductsFromServer = async () => {
    const res = await getMyProducts();
    if (res.success) {
      setProducts(res.data);
    } else {
      alert.error("Could not load my products!");
    }
  };

  useEffect(() => {
    getMyProductsFromServer();
  }, []);

  const deleteProduct = async (e, productId) => {
    e.preventDefault();
    if (!window.confirm("Are you sure, you want to delete this product ?"))
      return;

    const res = await deleteMyProductById(productId);
    if (res.success) {
      alert.success("Product was deleted!");
      getMyProductsFromServer();
    } else {
      alert.error(res.error);
    }
  };

  return (
    <Container className="mt-5">
      <h1 className="text-center">My Products</h1>
      <Link to="/my-products/add" className="btn btn-primary">
        Add new Product
      </Link>
      <hr />
      <Table striped>
        <thead>
          <tr>
            <th>#</th>
            <th>Title</th>
            <th>Price</th>
            <th>Due Date</th>
            <th>Released</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {products.map((p, i) => (
            <tr key={p.id}>
              <td>{i + 1}</td>
              <td>{p.title}</td>
              <td>{p.price}</td>
              <td>
                <Moment date={p.dueDate} format="M/D/yyyy" />{" "}
              </td>
              <td>{p.released ? "Yes" : "No"}</td>
              <td>
                {p.released ? (
                  ""
                ) : (
                  <>
                    <Link to={"/my-products/edit/" + p.id}>Edit</Link>{" "}
                    <Link onClick={(e) => deleteProduct(e, p.id)}> Delete</Link>
                  </>
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </Container>
  );
}
