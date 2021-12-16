package ru.ibs.company.framework.data;

import lombok.Data;
import lombok.NonNull;

@Data
public class User {
    @NonNull String login;
    @NonNull String password;
}
