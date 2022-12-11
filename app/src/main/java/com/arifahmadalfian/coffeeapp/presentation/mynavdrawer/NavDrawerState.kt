package com.arifahmadalfian.coffeeapp.presentation.mynavdrawer

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import com.arifahmadalfian.coffeeapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class NavDrawerState(
    val scaffoldState: ScaffoldState,
    private val scope: CoroutineScope,
    private val context: Context,
    private val activity: Activity
) {

    fun onMenuClick() {
        scope.launch {
            scaffoldState.drawerState.open()
        }
    }

    fun onItemSelected(title: String) {
        scope.launch {
            scaffoldState.drawerState.close()
            val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
                message = context.resources.getString(R.string.coming_soon, title),
                actionLabel = context.resources.getString(R.string.subscribe_question)
            )
            if (snackBarResult == SnackbarResult.ActionPerformed) {
                Toast.makeText(
                    context,
                    context.resources.getString(R.string.subscribed_info),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun onBackPressed() {
        if (scaffoldState.drawerState.isOpen) {
            scope.launch {
                scaffoldState.drawerState.close()
            }
        } else {
            activity.finish()
        }
    }
}

@Composable
fun rememberNavDrawerState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    context: Context = LocalContext.current,
    activity: Activity
): NavDrawerState =
    remember(scaffoldState, coroutineScope, context, activity) {
        NavDrawerState(
            scaffoldState = scaffoldState,
            scope = coroutineScope,
            context = context,
            activity = activity
        )
    }