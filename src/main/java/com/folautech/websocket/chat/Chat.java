package com.folautech.websocket.chat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.folautech.websocket.message.Message;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@DynamicUpdate
@Entity
@SQLDelete(sql = "UPDATE chats SET deleted = 1 WHERE id = ?", check = ResultCheckStyle.NONE)
@Where(clause = "deleted = 0")
@Table(name = "chats", indexes = {@Index(columnList = "uuid"), @Index(columnList = "deleted")})
public class Chat implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name = "uuid", unique = true, nullable = false, updatable = false)
    private String uuid;

    @Column(name = "title")
    private String title;

//    @JsonManagedReference
    @JsonIgnoreProperties(value = {"chat","user"})
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chat")
    private Set<Message> messages;

//    @JsonIgnoreProperties(value = {"chats"})
//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
//    @JoinTable(name = "chat_users", joinColumns = {@JoinColumn(name = "chat_id")},
//            inverseJoinColumns = {@JoinColumn(name = "user_id")})
//    private Set<User> users;

    @Column(name = "deleted", nullable = false)
    private boolean deleted;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

//    public void addUser(User user){
//        if(this.users==null){
//            this.users = new HashSet<>();
//        }
//        this.users.add(user);
//    }


    @PrePersist
    private void preCreate() {
        if (this.uuid == null || this.uuid.isEmpty()) {
            this.uuid = "chat-" + UUID.randomUUID().toString();
        }
    }
}
