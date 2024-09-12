package com.example.demo.domain.entities;

import com.example.demo.domain.base.EventType;

import jakarta.persistence.Entity;

@Entity
public class Outbox extends EventType<String> {

}
