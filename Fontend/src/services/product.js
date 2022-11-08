import axios from "axios";

const getAllProducts = async () => {
  return await axios.get("http://localhost:8080/api/product/all");
};

const getProductsByCategory = async (categoryName) => {
  return await axios.get(
    `http://localhost:8080/api/product/category?name=${categoryName}`
  );
};

const searchProduct = async (search) => {
  return await axios.get(
    `http://localhost:8080/api/product/search?name=${search}`
  );
};


const getProductsByFilter = async (filter) => {
  return await axios.get(`http://localhost:8080/api/product/${filter}`);
};

const createProduct = async (values) => {
  return await axios.post("http://localhost:8080/api/product/create", {
    productName: values.productName,
    productDesc: values.productDesc,
    productCost: values.productCost,

    productAmount: values.productAmount,
    productSale: values.productSale,
    dateSale: values.dateSale,
    categories: {
      categoryName: values.productCategory,
    },
  });
};

const updateProduct = async (id, values) => {
  return await axios.put("http://localhost:8080/api/product/update", {
    productID: id,
    productName: values.productName,
    productDesc: values.productDesc,
    productCost: values.productCost,

    productAmount: values.productAmount,
    productSale: values.productSale,
    dateSale: values.dateSale,
    categories: {
      categoryName: values.productCategory,
    },
  });
};

const deleteProduct = async (id) => {
  return await axios.delete(
    `http://localhost:8080/api/product/delete?id=${id}`
  );
};

const productRating = async (productID, userID, rating) => {
  return await axios.put(
    `http://localhost:8080/api/product/rating?id=${productID}`,
    {
      userID: userID,
      rating: rating,
    }
  );
};

const productUploadImages = async (formData) => {
  return await axios({
    method: "post",
    url: "http://localhost:8080/api/file/upload",
    data: formData,
    headers: {
      "Content-Type": "multipart/form-data",
      Authorization: `Bearer ${localStorage.getItem("token")}`,
    },
  });
};



export {
    getAllProducts,
    getProductsByCategory,
    getProductsByFilter,
    createProduct,
    updateProduct,
    deleteProduct,
    productRating,
    searchProduct,
    productUploadImages,
};