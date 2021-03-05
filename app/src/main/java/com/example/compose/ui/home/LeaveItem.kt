package com.example.compose.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.compose.R
import com.example.compose.common.Util.format
import com.example.compose.model.Leave

@Composable
fun LeaveItem(leave: Leave) {
    Card(
        modifier = Modifier.padding(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(4.dp)
        ) {
            LeaveCell(title = stringResource(id = R.string.requested_by), value = leave.requestedBy?.username!!)
            LeaveCell(title = stringResource(id = R.string.requested_date), value = format(leave.createDate))
            LeaveCell(title = stringResource(id = R.string.start_date), value = format(leave.startDate))
            LeaveCell(title = stringResource(id = R.string.end_date), value = format(leave.endDate))
            LeaveCell(title = stringResource(id = R.string.description), value = leave.description)
            LeaveCell(title = stringResource(id = R.string.comment), value = leave.comment)
            LeaveCell(title = stringResource(id = R.string.leave_description), value = leave.leaveTypes.description)
            LeaveCell(title = stringResource(id = R.string.leave_type), value = leave.leaveTypes.types)
        }
    }
}