package com.mpierucci.lasttraintolondon.lines.presentation


internal const val MOCK_RESPONSES_PATH = "mock-responses"

internal const val GET_LINES_STATUS_ENDPOINT = "/line/mode/tube,dlr,overground/status"

internal const val MOCK_LINES_STATUS_RESPONSE = "$MOCK_RESPONSES_PATH/lineStatusResponse.json"


internal val pathToFilesMap = mapOf(
    GET_LINES_STATUS_ENDPOINT to MOCK_LINES_STATUS_RESPONSE
)
