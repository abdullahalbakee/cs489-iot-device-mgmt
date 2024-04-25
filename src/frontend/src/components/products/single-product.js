import Card from "react-bootstrap/Card";
import { Link } from "react-router-dom";

export default function ({ product, showDetailsLink }) {
  const link = `https://picsum.photos/286/180?rand=${Math.random()}`;
  return (
    <Card style={{ width: "18rem" }}>
      <Card.Img variant="top" src={link} />
      <Card.Body>
        <Card.Title>
          <Link
            to={`/products/${product.id}`}
            style={{ textDecoration: "none" }}
          >
            {" "}
            {product.title}
          </Link>
        </Card.Title>
        <Card.Subtitle> ${product.price}</Card.Subtitle>
      </Card.Body>
    </Card>
  );
}
