import axios from "axios";

const getAllCategory = async () => {
  return await axios.get("http://localhost:8080/api/category/all");
};

const createCategory = async (data) => {
  return await axios.post("http://localhost:8080/api/category/create", {
    categoryName: data.categoryName,
    categoryDesc: data.categoryDesc,
  });
};

const updateCategory = async (id, data) => {
  return await axios.put("http://localhost:8080/api/category/update", {
    categoryID: id,
    categoryName: data.categoryName,
    categoryDesc: data.categoryDesc,
  });
};

const deleteCategory = async (id) => {
  return await axios.delete(`http://localhost:8080/api/category/delete?id=${id}`);
};

export { getAllCategory, createCategory, updateCategory, deleteCategory };
