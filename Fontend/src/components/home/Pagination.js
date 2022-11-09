import React from "react";
import style from "../../styles/pagination.module.css";

const Pagination = ({
  totalProduct,
  productPerPage,
  setCurrentPage,
  currentPage,
}) => {
  let pages = [];

  for (let i = 1; i <= Math.ceil(totalProduct / productPerPage); i++) {
    pages.push(i);
  }

  return (
    <div>
      {pages.map((page, index) => {
        return (
          <div className={style.pagination} key={index}>
            <button
              key={index}
              onClick={() => {
                setCurrentPage(page);
              }}
              className={page === currentPage ? "active" : ""}
            >
              {page}
            </button>
          </div>
        );
      })}
    </div>
  );
};

export default Pagination;
