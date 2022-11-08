import axios from "axios";

const getAllCartByUserId = async (userId) => {
  return await axios.get(`http://localhost:8080/api/cart?userID=${userId}`);
};

const getCartByCartId = async (cartId) => {
  return await axios.get(`http://localhost:8080/api/cart/id?cartID=${cartId}`);
};

const getCartWhenStatusIsFalse = async (userId) => {
  return await axios.get(
    `http://localhost:8080/api/cart/findByStatus?userId=${userId}`
  );
};

const createCart = async (userId) => {
  return await axios.post(
    "http://localhost:8080/api/cart/create?userID=" + userId
  );
};

const addProductToCart = async (productId, cartId) => {
  return await axios.put(
    `http://localhost:8080/api/cart/addProduct?productID=${productId}&cartID=${cartId}`
  );
};



const removeProductFromCart = async (productId, cartId) => {
  return await axios.put(
    `http://localhost:8080/api/cart/deleteProduct?productID=${productId}&cartID=${cartId}`
  );
};

const deleteCart = async (cartId) => {
  return await axios.delete(
    `http://localhost:8080/api/cart/delete?cartID=${cartId}`
  );
};

export {
  getAllCartByUserId,
  createCart,
  addProductToCart,
  removeProductFromCart,
  deleteCart,
  getCartWhenStatusIsFalse,
  getCartByCartId,
};
