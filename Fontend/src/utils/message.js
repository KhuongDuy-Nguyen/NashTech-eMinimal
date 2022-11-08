import {message} from 'antd';

const ShowMessage = (type, value) => {
    message[type]({
      content: value,
      className: "custom-class",
      style: {
        position: "absolute",
        top: "10vh",
        right: "10vw",
      },
    },2);
}

export default ShowMessage;