function item_modify(userId, foodId, foodCount){
    $.ajax({
        type: "GET",
        contentType: "application/text;charset=UTF-8",
        url: "CartItemModify?userId=" + userId + "&foodId=" + foodId + "&foodCount=" + foodCount,
        success: function(result) {
            console.log(result);
        },
        error: function(e){
            console.log(e.status);
            console.log(e.responseText);
        }
    });
}

function item_modify_minus(userId, foodId) {
    let foodCountSpan = $("#food-" + foodId)[0];
    let foodCount = parseInt(foodCountSpan.innerText);
    foodCount = Math.max(0, foodCount - 1);
    foodCountSpan.innerText = foodCount;
    item_modify(userId, foodId, foodCount);
}

function item_modify_plus(userId, foodId) {
    let foodCountSpan = $("#food-" + foodId)[0];
    let foodCount = parseInt(foodCountSpan.innerText);
    foodCount = Math.min(99, foodCount + 1);
    foodCountSpan.innerText = foodCount;
    item_modify(userId, foodId, foodCount);
}

function item_modify_zero(userId, foodId) {
    let foodCountSpan = $("#food-" + foodId)[0];
    foodCountSpan.innerText = 0;
    item_modify(userId, foodId, 0);
}

function item_modify_minus_in_cart(userId, foodId) {
    let foodCountSpan = $("#food-" + foodId)[0];
    let foodCount = parseInt(foodCountSpan.innerText);
    foodCount = Math.max(1, foodCount - 1);
    foodCountSpan.innerText = foodCount;
    let foodPriceSingleSpan = $("#food-price-single-" + foodId)[0];
    let foodPriceTotalSpan = $("#food-price-total-" + foodId)[0];
    foodPriceTotalSpan.innerText = (parseFloat(foodPriceSingleSpan.innerText) * foodCount).toFixed(2);
    item_modify(userId, foodId, foodCount);
    update_total_price();
}

function item_modify_plus_in_cart(userId, foodId) {
    let foodCountSpan = $("#food-" + foodId)[0];
    let foodCount = parseInt(foodCountSpan.innerText);
    foodCount = Math.min(99, foodCount + 1);
    foodCountSpan.innerText = foodCount;
    let foodPriceSingleSpan = $("#food-price-single-" + foodId)[0];
    let foodPriceTotalSpan = $("#food-price-total-" + foodId)[0];
    foodPriceTotalSpan.innerText = (parseFloat(foodPriceSingleSpan.innerText) * foodCount).toFixed(2);
    item_modify(userId, foodId, foodCount);
    update_total_price();
}

function item_modify_zero_in_cart(userId, foodId) {
    if (confirm("确定要从购物车中删除该菜品吗？")) {
        $("#food-table-row-" + foodId)[0].remove();
        item_modify(userId, foodId, 0);
        check_no_food_in_cart();
        update_total_price();
    }
}

function check_no_food_in_cart() {
    if ($(".food-table-row").length === 0) {
        let foodTable = $(".food-table");
        if (foodTable.length !== 0) foodTable[0].remove();
        $(".no-food-in-cart-alert")[0].style = "";
    }
}

function update_total_price(delta) {
    let food_price_total_spans = $(".food-price-total");
    let total = 0;
    for (let i = 0; i < food_price_total_spans.length; i++) {
        total += parseFloat(food_price_total_spans[i].innerText);
    }
    let total_price_span = $(".order-total-price")[0];
    total_price_span.innerText = total.toFixed(2);
}