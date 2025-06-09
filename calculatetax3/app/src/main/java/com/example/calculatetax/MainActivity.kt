package com.example.calculatetax

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculatetax.ui.theme.CalculatetaxTheme
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatetaxTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    TaxLayout()
                }
            }
        }
    }
}

@Composable
fun TaxLayout(modifier: Modifier = Modifier) {
    var amountInput by remember { mutableStateOf("") }
    var taxInput by remember { mutableStateOf("") }
    var roundUp by remember { mutableStateOf(false) }

    val amount = amountInput.toDoubleOrNull()
    val taxPercent = taxInput.toDoubleOrNull()
    val tax = if (amount != null && taxPercent != null) {
        calculateTax(amount, taxPercent, roundUp)
    } else {
        ""
    }

    Column(
        modifier = modifier
            .padding(horizontal = 40.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.calculate_tax),
            modifier = Modifier
                .padding(top = 40.dp, bottom = 16.dp)
                .align(Alignment.Start)
        )

        EditTextNumber(
            label = R.string.bill_amount,
            leadingIcon = R.drawable.baseline_attach_money_24,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            value = amountInput,
            onValueChange = { amountInput = it },
            isError = amountInput.isNotBlank() && amount == null,
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )

        EditTextNumber(
            label = R.string.tax_percentage,
            leadingIcon = R.drawable.baseline_bar_chart_24,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            value = taxInput,
            onValueChange = { taxInput = it },
            isError = taxInput.isNotBlank() && taxPercent == null,
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )

        RoundTax(
            roundUp = roundUp,
            onRoundUpChanged = { roundUp = it },
            modifier = Modifier.padding(bottom = 32.dp)
        )

        if (tax.isNotEmpty()) {
            Text(
                text = stringResource(R.string.tax_amount, tax),
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(150.dp))
    }
}

@Composable
fun EditTextNumber(
    @StringRes label: Int,
    keyboardOptions: KeyboardOptions,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    @DrawableRes leadingIcon: Int? = null,
    isError: Boolean = false
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(label)) },
        leadingIcon = leadingIcon?.let {
            {
                Icon(
                    painter = painterResource(id = it),
                    contentDescription = null
                )
            }
        },
        singleLine = true,
        keyboardOptions = keyboardOptions,
        isError = isError,
        modifier = modifier
    )
}

@Composable
fun RoundTax(
    roundUp: Boolean,
    onRoundUpChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(R.string.round_up_tax))
        Switch(
            checked = roundUp,
            onCheckedChange = onRoundUpChanged,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End)
        )
    }
}

private fun calculateTax(amount: Double, taxPercent: Double, roundUp: Boolean): String {
    var tax = taxPercent / 100 * amount
    if (roundUp) {
        tax = ceil(tax)
    }
    return NumberFormat.getCurrencyInstance().format(tax)
}

@Preview(showBackground = true)
@Composable
fun TaxLayoutPreview() {
    CalculatetaxTheme {
        TaxLayout()
    }
}
