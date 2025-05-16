package com.jyo.practiceprojectformList.Repository;//package com.jyo.practiceprojectformList.repository;

import com.jyo.practiceprojectformList.entity.Message;
import com.jyo.practiceprojectformList.entity.MessageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@EnableJpaRepositories
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByMessageType(MessageType type);
    List<Message> findBySenderEmailAndReceiverEmailOrReceiverEmailAndSenderEmail(
            String sender1, String receiver1, String sender2, String receiver2
    );

//    @Query("SELECT m FROM Message m WHERE " +
//            "((m.senderEmail = :user1 AND m.receiverEmail = :user2) OR " +
//            "(m.senderEmail = :user2 AND m.receiverEmail = :user1)) " +
//            "AND m.type = com.jyo.practiceprojectformList.entity.MessageType.PRIVATE " +
//            "ORDER BY m.timestamp ASC")
//    List<Message> findPrivateMessages(String user1, String user2);

    @Query("SELECT m FROM Message m WHERE (m.senderEmail = :senderEmail AND m.receiverEmail = :receiverEmail) OR (m.senderEmail = :receiverEmail AND m.receiverEmail = :senderEmail)")
    List<Message> findPrivateMessages(@Param("senderEmail") String senderEmail, @Param("receiverEmail") String receiverEmail);

    List<Message> findByReceiverEmailIsNullOrderByTimestampAsc();

}
