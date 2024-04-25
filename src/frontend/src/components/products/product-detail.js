import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Image from "react-bootstrap/Image";
import Table from "react-bootstrap/Table";
import Moment from "react-moment";
import { useContext, useEffect, useMemo, useRef, useState } from "react";
import { bidForProduct, getBids, getProduct } from "../../service/products";
import { useNavigate, useParams } from "react-router-dom";
import Product from "../../models/product";
import Button from "react-bootstrap/Button";
import UserContext from "../../context/user-context";
import { useToast } from "react-toastify";

export default function () {
  const alert = useToast();
  const navigate = useNavigate();
  const [product, setProduct] = useState(new Product());
  const [bids, setBids] = useState([]);
  const { accessToken } = useContext(UserContext);
  const params = useParams();
  const [productId] = useState(params.productId);
  const [maxBid, setMaxBid] = useState(0);
  const bidAmountRef = useRef(null);

  const getSingleProduct = async () => {
    const res = await getProduct(productId);
    if (res.success) {
      setProduct(res.data);
    } else {
      alert.error(res.error);
    }
  };

  const getProductBids = async () => {
    const res = await getBids(productId);
    if (res.success) {
      const bids = res.data;
      if (bids.length > 0) {
        setMaxBid(bids[0].amount);
        bidAmountRef.current.value = bids[0].amount + 1;
      }
      setBids(res.data);
    } else {
      alert.error(res.error);
    }
  };

  const placeBid = async () => {
    if (!accessToken) {
      navigate("/login");
      return;
    }
    const bidAmount = bidAmountRef.current.value;
    if (bidAmount.trim().length === 0) {
      alert.error("Bid amount can not be empty!");
      return;
    }
    const amount = Number(bidAmount.trim());
    if (amount <= maxBid) {
      alert.error(
        "Bid amount has to be higher than the current highest bid amount!"
      );
      return;
    }
    const res = await bidForProduct(productId, amount);
    if (res.success) {
      alert.success("Bid has been placed successfully!");
      bidAmountRef.current.value = amount + 1;
      getProductBids();
    } else {
      alert.error(
        "An unknown error occured while placing the bid. Please try again!"
      );
    }
  };

  useEffect(() => {
    getSingleProduct();
    getProductBids();
  }, []);

  const link = useMemo(
    () => `https://picsum.photos/600/400?rand=${Math.random()}`,
    []
  );
  return (
    <>
      <Container className="mt-5">
        <Row>
          <Col xs={12} md={6}>
            <Image src={link} rounded fluid />
          </Col>

          <Col xs={12} md={6}>
            <p className="h2">{product.title}</p>
            <p className="h4">Price: ${product.price}</p>
            <p className="h5">Highest Bid: ${maxBid}</p>
            <p className="mt-5 mb-0">Place a bid higher than {maxBid}</p>
            <input ref={bidAmountRef} type="number" />
            <Button
              onClick={placeBid}
              className="btn btn-primary m-3"
              disabled={false}
              title="Login to place bid"
            >
              Bid Now
            </Button>
            <p className="h5">
              Due Date : <Moment date={product.dueDate} format="M/D/yyyy" />
            </p>
            <p className="h3 mt-3">Description</p>
            <p>{product.description}</p>
          </Col>
        </Row>
        <Row className="mt-5">
          <div className="h4">Bid History</div>
          <hr />
          <Table striped>
            <thead>
              <tr>
                <th>#</th>
                <th>Bidder</th>
                <th>Bid Amount</th>
                <th>Bid Time</th>
              </tr>
            </thead>
            <tbody>
              {bids.map((b, i) => (
                <tr key={b.id}>
                  <td>{bids.length - i}</td>
                  <td>{b.bidder}</td>
                  <td>{b.amount}</td>
                  <td>
                    <Moment fromNow withTitle titleFormat="hh:mm:ss a M/D/yyyy">
                      {b.time}
                    </Moment>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </Row>
      </Container>
    </>
  );
}
