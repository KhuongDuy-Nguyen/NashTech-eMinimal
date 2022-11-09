
import ErrorAuth from "../utils/errorAuth";
import ShowMessage from "../utils/message";
import { addProductToCart, createCart } from "./cart";

const AddToCart = (productID) => {
  // Check if user is logged in

  let userID = localStorage.getItem("userId");

  if (userID === null) {
    ShowMessage("error","Please login to buy product");
    setTimeout(() => {
      window.location.href = "/auth";
    }, 2000);

  } else {
    // Check if user has cart
    if (localStorage.getItem("cartId") === null) {
      createCart(userID)
        .then((res) => {
          localStorage.setItem("cartId", res.data.cartID);
        })
        .catch((err) => {
          console.log(err);
          ErrorAuth(err);
        });
    } else {
      var cartId = localStorage.getItem("cartId");
      addProductToCart(productID, cartId)
        .then((res) => {
          ShowMessage("success","Add to cart success");
        })
        .catch((err) => {
          console.log(err);
          ErrorAuth(err);
        });
    }
  }
};

export default AddToCart;