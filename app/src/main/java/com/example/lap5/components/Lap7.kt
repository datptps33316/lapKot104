package com.example.lap5.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun Lap7() {

    data class Product(val id: String, val name: String, val price: String)

    val context = LocalContext.current
    var id by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var products by remember { mutableStateOf(emptyList<Product>()) }

    fun handleAddProduct() {
        if (products.none { it.id == id }) {
            products += Product(id, name, price)
            return
        } else {
            Toast.makeText(context, "id đã tồn tại", Toast.LENGTH_SHORT).show();
        }
    }

    fun handleRemove(id: String) {
        products = products.filter { it.id != id }.toMutableList();
    }

    fun handleClickItem(product: Product) {
        id = product.id
        name = product.name
        price = product.price
    }

    fun handleUpdateProduct() {
        val existingProductIndex = products.indexOfFirst { it.id == id }
        if (existingProductIndex != -1) {
            val updatedProduct = Product(id, name, price)
            products = products.toMutableList().apply {
                this[existingProductIndex] = updatedProduct
            }
        } else {
            products = products + Product(id, name, price)
        }
    }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),

        ) {
        OutlinedTextField(
            value = id,
            modifier = Modifier
                .padding(top = 30.dp)
                .fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFB5E4A4),
                unfocusedBorderColor = Color.Black
            ),
            shape = RoundedCornerShape(12.dp),
            onValueChange = { id = it }
        )
        OutlinedTextField(
            value = name,
            modifier = Modifier
                .fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFB5E4A4),
                unfocusedBorderColor = Color.Black
            ),
            shape = RoundedCornerShape(12.dp),
            onValueChange = { name = it }
        )
        OutlinedTextField(
            value = price,
            modifier = Modifier
                .fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFB5E4A4),
                unfocusedBorderColor = Color.Black
            ),
            shape = RoundedCornerShape(12.dp),
            onValueChange = { price = it }
        )
        Row {
            Button(onClick = { handleAddProduct() }) {
                Text(text = "add")
            }
            Button(onClick = { handleUpdateProduct() }) {
                Text(text = "update")
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            itemsIndexed(products) { index, product ->
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        handleClickItem(product)
                    }
                    .padding(10.dp)) {
                    Row {
                        Text(text = product.id)
                        Text(text = product.name)
                        Text(text = product.price)
                        Button(onClick = { handleRemove(product.id) }) {
                            Text(text = "xóa")
                        }
                    }
                }
            }
        }
    }

}


