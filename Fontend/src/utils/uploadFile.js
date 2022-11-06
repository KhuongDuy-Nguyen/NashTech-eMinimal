import axios from "axios";

export default function uploadFile(values, id) {
  for (var i = 0; i < values.productImages.fileList.length; i++) {
    // json.details.productImages.push(values.productImages.fileList[i].originFileObj);
    console.log(values.productImages.fileList[i].originFileObj);
    let formData = new FormData();
    formData.append("file", values.productImages.fileList[i].originFileObj);
    formData.append("id", id);
    axios({
      method: "post",
      url: "http://localhost:8080/api/file/upload",
      data: formData,
      headers: { 
        "Content-Type": "multipart/form-data" ,
        "Authorization": `Bearer ${localStorage.getItem("token")}`
    }
    })
      .then((res) => {
        console.log(res);
        // success("Upload image success");
      })
      .catch((err) => {
        console.log(err);
        // error("Upload image failed");
      });
  }
}