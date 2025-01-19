package com.francis.weatherap.core.connectivity.def

import kotlinx.coroutines.flow.Flow

/** Network connectivity status */
enum class ConnectivityStatus {
    /** Network connected */
    Connected,

    /** Network disconnected */
    Disconnected,
}

/** Monitors and broadcasts network connectivity status */
interface ConnectivityMonitor {

    /** Flow to broadcast network connectivity status */
    val connectedStatus: Flow<ConnectivityStatus>
}
