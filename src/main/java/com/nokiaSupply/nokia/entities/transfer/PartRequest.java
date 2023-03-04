package com.nokiaSupply.nokia.entities.transfer;

import lombok.NonNull;

public record PartRequest(@NonNull String name, @NonNull String manufaturer, @NonNull double price) {
}
