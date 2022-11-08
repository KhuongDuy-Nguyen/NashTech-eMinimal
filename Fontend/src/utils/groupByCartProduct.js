
const convert = (cart) => cart.reduce((group, product) => {
    const key = product.productName;
    const price = product.productCost;
    if (!group[key]) {
        group[key] = {
        ...product,
        quantity: 0,
        };
    }
    group[key].quantity += 1;
    group[key].productCost = price * group[key].quantity;
    
    return group;
}, {});

export default convert;