package com.example.fitbuddy.Entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BuddyConnectionRequest {
    private String userId;
    private boolean approved;

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == BuddyConnectionRequest.class) {
            BuddyConnectionRequest temp = (BuddyConnectionRequest) obj;
            return this.userId.equals(temp.getUserId());
        }
        return false;
    }
}
