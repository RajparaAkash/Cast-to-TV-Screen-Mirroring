package com.github.kunal52.pairing;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class Pairingmessage {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0014pairingmessage.proto\u0012\u0013com.kunal52.pairing\";\n\u000ePairingRequest\u0012\u0013\n\u000bclient_name\u0018\u0002 \u0001(\t\u0012\u0014\n\fservice_name\u0018\u0001 \u0001(\t\"(\n\u0011PairingRequestAck\u0012\u0013\n\u000bserver_name\u0018\u0001 \u0001(\t\"¤\u0002\n\u000fPairingEncoding\u0012?\n\u0004type\u0018\u0001 \u0001(\u000e21.com.kunal52.pairing.PairingEncoding.EncodingType\u0012\u0015\n\rsymbol_length\u0018\u0002 \u0001(\r\"¸\u0001\n\fEncodingType\u0012\u0019\n\u0015ENCODING_TYPE_UNKNOWN\u0010\u0000\u0012\u001e\n\u001aENCODING_TYPE_ALPHANUMERIC\u0010\u0001\u0012\u0019\n\u0015ENCODING_TYPE_NUMERIC\u0010\u0002\u0012\u001d\n\u0019ENCODING_TYPE_HEXADECIMAL\u0010\u0003\u0012\u0018\n\u0014ENCODING_TYPE_QRCODE\u0010\u0004\u0012\u0019\n\fUNRECOGNIZED\u0010ÿÿÿÿÿÿÿÿÿ\u0001\"Å\u0001\n\rPairingOption\u0012=\n\u000finput_encodings\u0018\u0001 \u0003(\u000b2$.com.kunal52.pairing.PairingEncoding\u0012>\n\u0010output_encodings\u0018\u0002 \u0003(\u000b2$.com.kunal52.pairing.PairingEncoding\u00125\n\u000epreferred_role\u0018\u0003 \u0001(\u000e2\u001d.com.kunal52.pairing.RoleType\"\u0001\n\u0014PairingConfiguration\u00126\n\bencoding\u0018\u0001 \u0001(\u000b2$.com.kunal52.pairing.PairingEncoding\u00122\n\u000bclient_role\u0018\u0002 \u0001(\u000e2\u001d.com.kunal52.pairing.RoleType\"\u0019\n\u0017PairingConfigurationAck\"\u001f\n\rPairingSecret\u0012\u000e\n\u0006secret\u0018\u0001 \u0001(\f\"\"\n\u0010PairingSecretAck\u0012\u000e\n\u0006secret\u0018\u0001 \u0001(\f\"â\u0005\n\u000ePairingMessage\u0012\u0018\n\u0010protocol_version\u0018\u0001 \u0001(\u0005\u0012:\n\u0006status\u0018\u0002 \u0001(\u000e2*.com.kunal52.pairing.PairingMessage.Status\u0012\u0014\n\frequest_case\u0018\u0003 \u0001(\u0005\u0012<\n\u000fpairing_request\u0018\n \u0001(\u000b2#.com.kunal52.pairing.PairingRequest\u0012C\n\u0013pairing_request_ack\u0018\u000b \u0001(\u000b2&.com.kunal52.pairing.PairingRequestAck\u0012:\n\u000epairing_option\u0018\u0014 \u0001(\u000b2\".com.kunal52.pairing.PairingOption\u0012H\n\u0015pairing_configuration\u0018\u001e \u0001(\u000b2).com.kunal52.pairing.PairingConfiguration\u0012O\n\u0019pairing_configuration_ack\u0018\u001f \u0001(\u000b2,.com.kunal52.pairing.PairingConfigurationAck\u0012:\n\u000epairing_secret\u0018( \u0001(\u000b2\".com.kunal52.pairing.PairingSecret\u0012A\n\u0012pairing_secret_ack\u0018) \u0001(\u000b2%.com.kunal52.pairing.PairingSecretAck\"\u0001\n\u0006Status\u0012\u000b\n\u0007UNKNOWN\u0010\u0000\u0012\u000e\n\tSTATUS_OK\u0010È\u0001\u0012\u0011\n\fSTATUS_ERROR\u0010\u0003\u0012\u001d\n\u0018STATUS_BAD_CONFIGURATION\u0010\u0003\u0012\u0016\n\u0011STATUS_BAD_SECRET\u0010\u0003\u0012\u0019\n\fUNRECOGNIZED\u0010ÿÿÿÿÿÿÿÿÿ\u0001*g\n\bRoleType\u0012\u0015\n\u0011ROLE_TYPE_UNKNOWN\u0010\u0000\u0012\u0013\n\u000fROLE_TYPE_INPUT\u0010\u0001\u0012\u0014\n\u0010ROLE_TYPE_OUTPUT\u0010\u0002\u0012\u0019\n\fUNRECOGNIZED\u0010ÿÿÿÿÿÿÿÿÿ\u0001b\u0006proto3"}, new Descriptors.FileDescriptor[0]);
    private static final Descriptors.Descriptor internal_static_com_kunal52_pairing_PairingConfigurationAck_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_com_kunal52_pairing_PairingConfigurationAck_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_com_kunal52_pairing_PairingConfiguration_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_com_kunal52_pairing_PairingConfiguration_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_com_kunal52_pairing_PairingEncoding_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_com_kunal52_pairing_PairingEncoding_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_com_kunal52_pairing_PairingMessage_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_com_kunal52_pairing_PairingMessage_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_com_kunal52_pairing_PairingOption_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_com_kunal52_pairing_PairingOption_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_com_kunal52_pairing_PairingRequestAck_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_com_kunal52_pairing_PairingRequestAck_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_com_kunal52_pairing_PairingRequest_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_com_kunal52_pairing_PairingRequest_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_com_kunal52_pairing_PairingSecretAck_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_com_kunal52_pairing_PairingSecretAck_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_com_kunal52_pairing_PairingSecret_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_com_kunal52_pairing_PairingSecret_fieldAccessorTable;

    public interface PairingConfigurationAckOrBuilder extends MessageOrBuilder {
    }

    public interface PairingConfigurationOrBuilder extends MessageOrBuilder {
        RoleType getClientRole();

        int getClientRoleValue();

        PairingEncoding getEncoding();

        PairingEncodingOrBuilder getEncodingOrBuilder();

        boolean hasEncoding();
    }

    public interface PairingEncodingOrBuilder extends MessageOrBuilder {
        int getSymbolLength();

        PairingEncoding.EncodingType getType();

        int getTypeValue();
    }

    public interface PairingMessageOrBuilder extends MessageOrBuilder {
        PairingConfiguration getPairingConfiguration();

        PairingConfigurationAck getPairingConfigurationAck();

        PairingConfigurationAckOrBuilder getPairingConfigurationAckOrBuilder();

        PairingConfigurationOrBuilder getPairingConfigurationOrBuilder();

        PairingOption getPairingOption();

        PairingOptionOrBuilder getPairingOptionOrBuilder();

        PairingRequest getPairingRequest();

        PairingRequestAck getPairingRequestAck();

        PairingRequestAckOrBuilder getPairingRequestAckOrBuilder();

        PairingRequestOrBuilder getPairingRequestOrBuilder();

        PairingSecret getPairingSecret();

        PairingSecretAck getPairingSecretAck();

        PairingSecretAckOrBuilder getPairingSecretAckOrBuilder();

        PairingSecretOrBuilder getPairingSecretOrBuilder();

        int getProtocolVersion();

        int getRequestCase();

        PairingMessage.Status getStatus();

        int getStatusValue();

        boolean hasPairingConfiguration();

        boolean hasPairingConfigurationAck();

        boolean hasPairingOption();

        boolean hasPairingRequest();

        boolean hasPairingRequestAck();

        boolean hasPairingSecret();

        boolean hasPairingSecretAck();
    }

    public interface PairingOptionOrBuilder extends MessageOrBuilder {
        PairingEncoding getInputEncodings(int i);

        int getInputEncodingsCount();

        List<PairingEncoding> getInputEncodingsList();

        PairingEncodingOrBuilder getInputEncodingsOrBuilder(int i);

        List<? extends PairingEncodingOrBuilder> getInputEncodingsOrBuilderList();

        PairingEncoding getOutputEncodings(int i);

        int getOutputEncodingsCount();

        List<PairingEncoding> getOutputEncodingsList();

        PairingEncodingOrBuilder getOutputEncodingsOrBuilder(int i);

        List<? extends PairingEncodingOrBuilder> getOutputEncodingsOrBuilderList();

        RoleType getPreferredRole();

        int getPreferredRoleValue();
    }

    public interface PairingRequestAckOrBuilder extends MessageOrBuilder {
        String getServerName();

        ByteString getServerNameBytes();
    }

    public interface PairingRequestOrBuilder extends MessageOrBuilder {
        String getClientName();

        ByteString getClientNameBytes();

        String getServiceName();

        ByteString getServiceNameBytes();
    }

    public interface PairingSecretAckOrBuilder extends MessageOrBuilder {
        ByteString getSecret();
    }

    public interface PairingSecretOrBuilder extends MessageOrBuilder {
        ByteString getSecret();
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private Pairingmessage() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public enum RoleType implements ProtocolMessageEnum {
        ROLE_TYPE_UNKNOWN(0),
        ROLE_TYPE_INPUT(1),
        ROLE_TYPE_OUTPUT(2),
        UNRECOGNIZED(-1);
        
        public static final int ROLE_TYPE_INPUT_VALUE = 1;
        public static final int ROLE_TYPE_OUTPUT_VALUE = 2;
        public static final int ROLE_TYPE_UNKNOWN_VALUE = 0;
        public static final int UNRECOGNIZED_VALUE = -1;
        private static final RoleType[] VALUES = values();
        private static final Internal.EnumLiteMap<RoleType> internalValueMap = new Internal.EnumLiteMap<RoleType>() {

            @Override
            public RoleType findValueByNumber(int i) {
                return RoleType.forNumber(i);
            }
        };
        private final int value;

        @Override
        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        @Deprecated
        public static RoleType valueOf(int i) {
            return forNumber(i);
        }

        public static RoleType forNumber(int i) {
            if (i == -1) {
                return UNRECOGNIZED;
            }
            if (i == 0) {
                return ROLE_TYPE_UNKNOWN;
            }
            if (i == 1) {
                return ROLE_TYPE_INPUT;
            }
            if (i != 2) {
                return null;
            }
            return ROLE_TYPE_OUTPUT;
        }

        public static Internal.EnumLiteMap<RoleType> internalGetValueMap() {
            return internalValueMap;
        }

        @Override
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(ordinal());
        }

        @Override
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return Pairingmessage.getDescriptor().getEnumTypes().get(0);
        }

        public static RoleType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            } else if (enumValueDescriptor.getIndex() == -1) {
                return UNRECOGNIZED;
            } else {
                return VALUES[enumValueDescriptor.getIndex()];
            }
        }

        private RoleType(int i) {
            this.value = i;
        }
    }

    public static final class PairingRequest extends GeneratedMessageV3 implements PairingRequestOrBuilder {
        public static final int CLIENT_NAME_FIELD_NUMBER = 2;
        private static final PairingRequest DEFAULT_INSTANCE = new PairingRequest();
        private static final Parser<PairingRequest> PARSER = new AbstractParser<PairingRequest>() {

            @Override
            public PairingRequest parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new PairingRequest(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int SERVICE_NAME_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private volatile Object clientName_;
        private byte memoizedIsInitialized;
        private volatile Object serviceName_;

        private PairingRequest(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
        }

        private PairingRequest() {
            this.memoizedIsInitialized = -1;
            this.clientName_ = "";
            this.serviceName_ = "";
        }

        
        @Override
        public Object newInstance(UnusedPrivateParameter unusedPrivateParameter) {
            return new PairingRequest();
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

//        private PairingRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
//            this();
//            Objects.requireNonNull(extensionRegistryLite);
//            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
//            boolean z = false;
//            while (!z) {
//                try {
//                    int readTag = codedInputStream.readTag();
//                    if (readTag != 0) {
//                        if (readTag == 10) {
//                            this.serviceName_ = codedInputStream.readStringRequireUtf8();
//                        } else if (readTag == 18) {
//                            this.clientName_ = codedInputStream.readStringRequireUtf8();
//                        } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
//                        }
//                    }
//                    z = true;
//                } catch (InvalidProtocolBufferException e) {
//                    throw e.setUnfinishedMessage(this);
//                } catch (IOException e2) {
//                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
//                } catch (Throwable th) {
//                    this.unknownFields = newBuilder.build();
//                    makeExtensionsImmutable();
//                    throw th;
//                }
//            }
//            this.unknownFields = newBuilder.build();
//            makeExtensionsImmutable();
//        }
        private PairingRequest(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                    com.google.protobuf.UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        case 10: {
                            String s = input.readStringRequireUtf8();

                            serviceName_ = s;
                            break;
                        }
                        case 18: {
                            String s = input.readStringRequireUtf8();

                            clientName_ = s;
                            break;
                        }
                        default: {
                            if (!parseUnknownField(
                                    input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                            break;
                        }
                    }
                }
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new com.google.protobuf.InvalidProtocolBufferException(
                        e).setUnfinishedMessage(this);
            } finally {
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }


        public static final Descriptors.Descriptor getDescriptor() {
            return Pairingmessage.internal_static_com_kunal52_pairing_PairingRequest_descriptor;
        }

        
        @Override
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Pairingmessage.internal_static_com_kunal52_pairing_PairingRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(PairingRequest.class, Builder.class);
        }

        @Override
        public String getClientName() {
            Object obj = this.clientName_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.clientName_ = stringUtf8;
            return stringUtf8;
        }

        @Override
        public ByteString getClientNameBytes() {
            Object obj = this.clientName_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.clientName_ = copyFromUtf8;
            return copyFromUtf8;
        }

        @Override
        public String getServiceName() {
            Object obj = this.serviceName_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.serviceName_ = stringUtf8;
            return stringUtf8;
        }

        @Override
        public ByteString getServiceNameBytes() {
            Object obj = this.serviceName_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.serviceName_ = copyFromUtf8;
            return copyFromUtf8;
        }

        @Override
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!getServiceNameBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.serviceName_);
            }
            if (!getClientNameBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.clientName_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!getServiceNameBytes().isEmpty()) {
                i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.serviceName_);
            }
            if (!getClientNameBytes().isEmpty()) {
                i2 += GeneratedMessageV3.computeStringSize(2, this.clientName_);
            }
            int serializedSize = i2 + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof PairingRequest)) {
                return super.equals(obj);
            }
            PairingRequest pairingRequest = (PairingRequest) obj;
            if (getClientName().equals(pairingRequest.getClientName()) && getServiceName().equals(pairingRequest.getServiceName()) && this.unknownFields.equals(pairingRequest.unknownFields)) {
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 2) * 53) + getClientName().hashCode()) * 37) + 1) * 53) + getServiceName().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static PairingRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static PairingRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static PairingRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static PairingRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static PairingRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static PairingRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static PairingRequest parseFrom(InputStream inputStream) throws IOException {
            return (PairingRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static PairingRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PairingRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static PairingRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (PairingRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static PairingRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PairingRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static PairingRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (PairingRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static PairingRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PairingRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(PairingRequest pairingRequest) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(pairingRequest);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        
        @Override
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements PairingRequestOrBuilder {
            private Object clientName_;
            private Object serviceName_;

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Pairingmessage.internal_static_com_kunal52_pairing_PairingRequest_descriptor;
            }

            @Override
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Pairingmessage.internal_static_com_kunal52_pairing_PairingRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(PairingRequest.class, Builder.class);
            }

            private Builder() {
                this.clientName_ = "";
                this.serviceName_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.clientName_ = "";
                this.serviceName_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = PairingRequest.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.clientName_ = "";
                this.serviceName_ = "";
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return Pairingmessage.internal_static_com_kunal52_pairing_PairingRequest_descriptor;
            }

            @Override
            public PairingRequest getDefaultInstanceForType() {
                return PairingRequest.getDefaultInstance();
            }

            @Override
            public PairingRequest build() {
                PairingRequest buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public PairingRequest buildPartial() {
                PairingRequest pairingRequest = new PairingRequest(this);
                pairingRequest.clientName_ = this.clientName_;
                pairingRequest.serviceName_ = this.serviceName_;
                onBuilt();
                return pairingRequest;
            }

            @Override
            public Builder clone() {
                return (Builder) super.clone();
            }

            @Override
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override
            public Builder mergeFrom(Message message) {
                if (message instanceof PairingRequest) {
                    return mergeFrom((PairingRequest) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(PairingRequest pairingRequest) {
                if (pairingRequest == PairingRequest.getDefaultInstance()) {
                    return this;
                }
                if (!pairingRequest.getClientName().isEmpty()) {
                    this.clientName_ = pairingRequest.clientName_;
                    onChanged();
                }
                if (!pairingRequest.getServiceName().isEmpty()) {
                    this.serviceName_ = pairingRequest.serviceName_;
                    onChanged();
                }
                mergeUnknownFields(pairingRequest.unknownFields);
                onChanged();
                return this;
            }

            /* CODE  */
            @Override
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                PairingRequest pairingRequest = null;
                try {
                    pairingRequest = (PairingRequest) PairingRequest.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                } catch (InvalidProtocolBufferException e) {
                    pairingRequest = (PairingRequest) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (pairingRequest != null) {
                        mergeFrom(pairingRequest);
                    }
                }
                return this;
            }

            @Override
            public String getClientName() {
                Object obj = this.clientName_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.clientName_ = stringUtf8;
                return stringUtf8;
            }

            @Override
            public ByteString getClientNameBytes() {
                Object obj = this.clientName_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.clientName_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setClientName(String str) {
                Objects.requireNonNull(str);
                this.clientName_ = str;
                onChanged();
                return this;
            }

            public Builder clearClientName() {
                this.clientName_ = PairingRequest.getDefaultInstance().getClientName();
                onChanged();
                return this;
            }

            public Builder setClientNameBytes(ByteString byteString) {
                Objects.requireNonNull(byteString);
                PairingRequest.checkByteStringIsUtf8(byteString);
                this.clientName_ = byteString;
                onChanged();
                return this;
            }

            @Override
            public String getServiceName() {
                Object obj = this.serviceName_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.serviceName_ = stringUtf8;
                return stringUtf8;
            }

            @Override
            public ByteString getServiceNameBytes() {
                Object obj = this.serviceName_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.serviceName_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setServiceName(String str) {
                Objects.requireNonNull(str);
                this.serviceName_ = str;
                onChanged();
                return this;
            }

            public Builder clearServiceName() {
                this.serviceName_ = PairingRequest.getDefaultInstance().getServiceName();
                onChanged();
                return this;
            }

            public Builder setServiceNameBytes(ByteString byteString) {
                Objects.requireNonNull(byteString);
                PairingRequest.checkByteStringIsUtf8(byteString);
                this.serviceName_ = byteString;
                onChanged();
                return this;
            }

            @Override
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }

        public static PairingRequest getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<PairingRequest> parser() {
            return PARSER;
        }

        @Override
        public Parser<PairingRequest> getParserForType() {
            return PARSER;
        }

        @Override
        public PairingRequest getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static final class PairingRequestAck extends GeneratedMessageV3 implements PairingRequestAckOrBuilder {
        private static final PairingRequestAck DEFAULT_INSTANCE = new PairingRequestAck();
        private static final Parser<PairingRequestAck> PARSER = new AbstractParser<PairingRequestAck>() {

            @Override
            public PairingRequestAck parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new PairingRequestAck(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int SERVER_NAME_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private volatile Object serverName_;

        private PairingRequestAck(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
        }

        private PairingRequestAck() {
            this.memoizedIsInitialized = -1;
            this.serverName_ = "";
        }

        @Override
        public Object newInstance(UnusedPrivateParameter unusedPrivateParameter) {
            return new PairingRequestAck();
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

//        private PairingRequestAck(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
//            this();
//            Objects.requireNonNull(extensionRegistryLite);
//            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
//            boolean z = false;
//            while (!z) {
//                try {
//                    int readTag = codedInputStream.readTag();
//                    if (readTag != 0) {
//                        if (readTag == 10) {
//                            this.serverName_ = codedInputStream.readStringRequireUtf8();
//                        } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
//                        }
//                    }
//                    z = true;
//                } catch (InvalidProtocolBufferException e) {
//                    throw e.setUnfinishedMessage(this);
//                } catch (IOException e2) {
//                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
//                } catch (Throwable th) {
//                    this.unknownFields = newBuilder.build();
//                    makeExtensionsImmutable();
//                    throw th;
//                }
//            }
//            this.unknownFields = newBuilder.build();
//            makeExtensionsImmutable();
//        }
        private PairingRequestAck(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                    com.google.protobuf.UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        case 10: {
                            String s = input.readStringRequireUtf8();

                            serverName_ = s;
                            break;
                        }
                        default: {
                            if (!parseUnknownField(
                                    input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                            break;
                        }
                    }
                }
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new com.google.protobuf.InvalidProtocolBufferException(
                        e).setUnfinishedMessage(this);
            } finally {
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return Pairingmessage.internal_static_com_kunal52_pairing_PairingRequestAck_descriptor;
        }

        @Override
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Pairingmessage.internal_static_com_kunal52_pairing_PairingRequestAck_fieldAccessorTable.ensureFieldAccessorsInitialized(PairingRequestAck.class, Builder.class);
        }

        @Override
        public String getServerName() {
            Object obj = this.serverName_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.serverName_ = stringUtf8;
            return stringUtf8;
        }

        @Override
        public ByteString getServerNameBytes() {
            Object obj = this.serverName_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.serverName_ = copyFromUtf8;
            return copyFromUtf8;
        }

        @Override
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!getServerNameBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.serverName_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!getServerNameBytes().isEmpty()) {
                i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.serverName_);
            }
            int serializedSize = i2 + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof PairingRequestAck)) {
                return super.equals(obj);
            }
            PairingRequestAck pairingRequestAck = (PairingRequestAck) obj;
            if (getServerName().equals(pairingRequestAck.getServerName()) && this.unknownFields.equals(pairingRequestAck.unknownFields)) {
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getServerName().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static PairingRequestAck parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static PairingRequestAck parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static PairingRequestAck parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static PairingRequestAck parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static PairingRequestAck parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static PairingRequestAck parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static PairingRequestAck parseFrom(InputStream inputStream) throws IOException {
            return (PairingRequestAck) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static PairingRequestAck parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PairingRequestAck) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static PairingRequestAck parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (PairingRequestAck) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static PairingRequestAck parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PairingRequestAck) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static PairingRequestAck parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (PairingRequestAck) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static PairingRequestAck parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PairingRequestAck) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(PairingRequestAck pairingRequestAck) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(pairingRequestAck);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements PairingRequestAckOrBuilder {
            private Object serverName_;

            @Override
            public boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Pairingmessage.internal_static_com_kunal52_pairing_PairingRequestAck_descriptor;
            }

            @Override
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Pairingmessage.internal_static_com_kunal52_pairing_PairingRequestAck_fieldAccessorTable.ensureFieldAccessorsInitialized(PairingRequestAck.class, Builder.class);
            }

            private Builder() {
                this.serverName_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.serverName_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = PairingRequestAck.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.serverName_ = "";
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return Pairingmessage.internal_static_com_kunal52_pairing_PairingRequestAck_descriptor;
            }

            @Override
            public PairingRequestAck getDefaultInstanceForType() {
                return PairingRequestAck.getDefaultInstance();
            }

            @Override
            public PairingRequestAck build() {
                PairingRequestAck buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public PairingRequestAck buildPartial() {
                PairingRequestAck pairingRequestAck = new PairingRequestAck(this);
                pairingRequestAck.serverName_ = this.serverName_;
                onBuilt();
                return pairingRequestAck;
            }

            @Override
            public Builder clone() {
                return (Builder) super.clone();
            }

            @Override
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override
            public Builder mergeFrom(Message message) {
                if (message instanceof PairingRequestAck) {
                    return mergeFrom((PairingRequestAck) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(PairingRequestAck pairingRequestAck) {
                if (pairingRequestAck == PairingRequestAck.getDefaultInstance()) {
                    return this;
                }
                if (!pairingRequestAck.getServerName().isEmpty()) {
                    this.serverName_ = pairingRequestAck.serverName_;
                    onChanged();
                }
                mergeUnknownFields(pairingRequestAck.unknownFields);
                onChanged();
                return this;
            }

            /* CODE  */
            @Override
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                PairingRequestAck pairingRequestAck = null;
                try {
                    pairingRequestAck = (PairingRequestAck) PairingRequestAck.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                } catch (InvalidProtocolBufferException e) {
                    pairingRequestAck = (PairingRequestAck) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (pairingRequestAck != null) {
                        mergeFrom(pairingRequestAck);
                    }
                }
                return this;
            }


            @Override
            public String getServerName() {
                Object obj = this.serverName_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.serverName_ = stringUtf8;
                return stringUtf8;
            }

            @Override
            public ByteString getServerNameBytes() {
                Object obj = this.serverName_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.serverName_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setServerName(String str) {
                Objects.requireNonNull(str);
                this.serverName_ = str;
                onChanged();
                return this;
            }

            public Builder clearServerName() {
                this.serverName_ = PairingRequestAck.getDefaultInstance().getServerName();
                onChanged();
                return this;
            }

            public Builder setServerNameBytes(ByteString byteString) {
                Objects.requireNonNull(byteString);
                PairingRequestAck.checkByteStringIsUtf8(byteString);
                this.serverName_ = byteString;
                onChanged();
                return this;
            }

            @Override
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }

        public static PairingRequestAck getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<PairingRequestAck> parser() {
            return PARSER;
        }

        @Override
        public Parser<PairingRequestAck> getParserForType() {
            return PARSER;
        }

        @Override
        public PairingRequestAck getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static final class PairingEncoding extends GeneratedMessageV3 implements PairingEncodingOrBuilder {
        private static final PairingEncoding DEFAULT_INSTANCE = new PairingEncoding();
        private static final Parser<PairingEncoding> PARSER = new AbstractParser<PairingEncoding>() {

            @Override
            public PairingEncoding parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new PairingEncoding(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int SYMBOL_LENGTH_FIELD_NUMBER = 2;
        public static final int TYPE_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private int symbolLength_;
        private int type_;

        private PairingEncoding(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
        }

        private PairingEncoding() {
            this.memoizedIsInitialized = -1;
            this.type_ = 0;
        }


        @Override
        public Object newInstance(UnusedPrivateParameter unusedPrivateParameter) {
            return new PairingEncoding();
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

//        private PairingEncoding(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
//            this();
//            Objects.requireNonNull(extensionRegistryLite);
//            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
//            boolean z = false;
//            while (!z) {
//                try {
//                    int readTag = codedInputStream.readTag();
//                    if (readTag != 0) {
//                        if (readTag == 8) {
//                            this.type_ = codedInputStream.readEnum();
//                        } else if (readTag == 16) {
//                            this.symbolLength_ = codedInputStream.readUInt32();
//                        } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
//                        }
//                    }
//                    z = true;
//                } catch (InvalidProtocolBufferException e) {
//                    throw e.setUnfinishedMessage(this);
//                } catch (IOException e2) {
//                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
//                } catch (Throwable th) {
//                    this.unknownFields = newBuilder.build();
//                    makeExtensionsImmutable();
//                    throw th;
//                }
//            }
//            this.unknownFields = newBuilder.build();
//            makeExtensionsImmutable();
//        }
        private PairingEncoding(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                    com.google.protobuf.UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        case 8: {
                            int rawValue = input.readEnum();

                            type_ = rawValue;
                            break;
                        }
                        case 16: {

                            symbolLength_ = input.readUInt32();
                            break;
                        }
                        default: {
                            if (!parseUnknownField(
                                    input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                            break;
                        }
                    }
                }
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new com.google.protobuf.InvalidProtocolBufferException(
                        e).setUnfinishedMessage(this);
            } finally {
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return Pairingmessage.internal_static_com_kunal52_pairing_PairingEncoding_descriptor;
        }

        
        @Override
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Pairingmessage.internal_static_com_kunal52_pairing_PairingEncoding_fieldAccessorTable.ensureFieldAccessorsInitialized(PairingEncoding.class, Builder.class);
        }

        public enum EncodingType implements ProtocolMessageEnum {
            ENCODING_TYPE_UNKNOWN(0),
            ENCODING_TYPE_ALPHANUMERIC(1),
            ENCODING_TYPE_NUMERIC(2),
            ENCODING_TYPE_HEXADECIMAL(3),
            ENCODING_TYPE_QRCODE(4),
            UNRECOGNIZED(-1);
            
            public static final int ENCODING_TYPE_ALPHANUMERIC_VALUE = 1;
            public static final int ENCODING_TYPE_HEXADECIMAL_VALUE = 3;
            public static final int ENCODING_TYPE_NUMERIC_VALUE = 2;
            public static final int ENCODING_TYPE_QRCODE_VALUE = 4;
            public static final int ENCODING_TYPE_UNKNOWN_VALUE = 0;
            public static final int UNRECOGNIZED_VALUE = -1;
            private static final EncodingType[] VALUES = values();
            private static final Internal.EnumLiteMap<EncodingType> internalValueMap = new Internal.EnumLiteMap<EncodingType>() {

                @Override
                public EncodingType findValueByNumber(int i) {
                    return EncodingType.forNumber(i);
                }
            };
            private final int value;

            @Override
            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.value;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }

            @Deprecated
            public static EncodingType valueOf(int i) {
                return forNumber(i);
            }

            public static EncodingType forNumber(int i) {
                if (i == -1) {
                    return UNRECOGNIZED;
                }
                if (i == 0) {
                    return ENCODING_TYPE_UNKNOWN;
                }
                if (i == 1) {
                    return ENCODING_TYPE_ALPHANUMERIC;
                }
                if (i == 2) {
                    return ENCODING_TYPE_NUMERIC;
                }
                if (i == 3) {
                    return ENCODING_TYPE_HEXADECIMAL;
                }
                if (i != 4) {
                    return null;
                }
                return ENCODING_TYPE_QRCODE;
            }

            public static Internal.EnumLiteMap<EncodingType> internalGetValueMap() {
                return internalValueMap;
            }

            @Override
            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                return getDescriptor().getValues().get(ordinal());
            }

            @Override
            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return getDescriptor();
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return PairingEncoding.getDescriptor().getEnumTypes().get(0);
            }

            public static EncodingType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
                if (enumValueDescriptor.getType() != getDescriptor()) {
                    throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                } else if (enumValueDescriptor.getIndex() == -1) {
                    return UNRECOGNIZED;
                } else {
                    return VALUES[enumValueDescriptor.getIndex()];
                }
            }

            private EncodingType(int i) {
                this.value = i;
            }
        }

        @Override
        public int getTypeValue() {
            return this.type_;
        }

        @Override
        public EncodingType getType() {
            EncodingType valueOf = EncodingType.valueOf(this.type_);
            return valueOf == null ? EncodingType.UNRECOGNIZED : valueOf;
        }

        @Override
        public int getSymbolLength() {
            return this.symbolLength_;
        }

        @Override
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.type_ != EncodingType.ENCODING_TYPE_UNKNOWN.getNumber()) {
                codedOutputStream.writeEnum(1, this.type_);
            }
            int i = this.symbolLength_;
            if (i != 0) {
                codedOutputStream.writeUInt32(2, i);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.type_ != EncodingType.ENCODING_TYPE_UNKNOWN.getNumber()) {
                i2 = 0 + CodedOutputStream.computeEnumSize(1, this.type_);
            }
            int i3 = this.symbolLength_;
            if (i3 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(2, i3);
            }
            int serializedSize = i2 + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof PairingEncoding)) {
                return super.equals(obj);
            }
            PairingEncoding pairingEncoding = (PairingEncoding) obj;
            if (this.type_ == pairingEncoding.type_ && getSymbolLength() == pairingEncoding.getSymbolLength() && this.unknownFields.equals(pairingEncoding.unknownFields)) {
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + this.type_) * 37) + 2) * 53) + getSymbolLength()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static PairingEncoding parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static PairingEncoding parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static PairingEncoding parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static PairingEncoding parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static PairingEncoding parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static PairingEncoding parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static PairingEncoding parseFrom(InputStream inputStream) throws IOException {
            return (PairingEncoding) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static PairingEncoding parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PairingEncoding) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static PairingEncoding parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (PairingEncoding) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static PairingEncoding parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PairingEncoding) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static PairingEncoding parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (PairingEncoding) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static PairingEncoding parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PairingEncoding) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(PairingEncoding pairingEncoding) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(pairingEncoding);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        
        @Override
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements PairingEncodingOrBuilder {
            private int symbolLength_;
            private int type_;

            @Override
            public boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Pairingmessage.internal_static_com_kunal52_pairing_PairingEncoding_descriptor;
            }

            
            @Override
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Pairingmessage.internal_static_com_kunal52_pairing_PairingEncoding_fieldAccessorTable.ensureFieldAccessorsInitialized(PairingEncoding.class, Builder.class);
            }

            private Builder() {
                this.type_ = 0;
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.type_ = 0;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = PairingEncoding.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.type_ = 0;
                this.symbolLength_ = 0;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return Pairingmessage.internal_static_com_kunal52_pairing_PairingEncoding_descriptor;
            }

            @Override
            public PairingEncoding getDefaultInstanceForType() {
                return PairingEncoding.getDefaultInstance();
            }

            @Override
            public PairingEncoding build() {
                PairingEncoding buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public PairingEncoding buildPartial() {
                PairingEncoding pairingEncoding = new PairingEncoding(this);
                pairingEncoding.type_ = this.type_;
                pairingEncoding.symbolLength_ = this.symbolLength_;
                onBuilt();
                return pairingEncoding;
            }

            @Override
            public Builder clone() {
                return (Builder) super.clone();
            }

            @Override
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override
            public Builder mergeFrom(Message message) {
                if (message instanceof PairingEncoding) {
                    return mergeFrom((PairingEncoding) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(PairingEncoding pairingEncoding) {
                if (pairingEncoding == PairingEncoding.getDefaultInstance()) {
                    return this;
                }
                if (pairingEncoding.type_ != 0) {
                    setTypeValue(pairingEncoding.getTypeValue());
                }
                if (pairingEncoding.getSymbolLength() != 0) {
                    setSymbolLength(pairingEncoding.getSymbolLength());
                }
                mergeUnknownFields(pairingEncoding.unknownFields);
                onChanged();
                return this;
            }

            /* CODE  */
            @Override
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                PairingEncoding pairingEncoding = null;
                try {
                    pairingEncoding = (PairingEncoding) PairingEncoding.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                } catch (InvalidProtocolBufferException e) {
                    pairingEncoding = (PairingEncoding) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (pairingEncoding != null) {
                        mergeFrom(pairingEncoding);
                    }
                }
                return this;
            }

            @Override
            public int getTypeValue() {
                return this.type_;
            }

            public Builder setTypeValue(int i) {
                this.type_ = i;
                onChanged();
                return this;
            }

            @Override
            public EncodingType getType() {
                EncodingType valueOf = EncodingType.valueOf(this.type_);
                return valueOf == null ? EncodingType.UNRECOGNIZED : valueOf;
            }

            public Builder setType(EncodingType encodingType) {
                Objects.requireNonNull(encodingType);
                this.type_ = encodingType.getNumber();
                onChanged();
                return this;
            }

            public Builder clearType() {
                this.type_ = 0;
                onChanged();
                return this;
            }

            @Override
            public int getSymbolLength() {
                return this.symbolLength_;
            }

            public Builder setSymbolLength(int i) {
                this.symbolLength_ = i;
                onChanged();
                return this;
            }

            public Builder clearSymbolLength() {
                this.symbolLength_ = 0;
                onChanged();
                return this;
            }

            @Override
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }

        public static PairingEncoding getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<PairingEncoding> parser() {
            return PARSER;
        }

        @Override
        public Parser<PairingEncoding> getParserForType() {
            return PARSER;
        }

        @Override
        public PairingEncoding getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static final class PairingOption extends GeneratedMessageV3 implements PairingOptionOrBuilder {
        private static final PairingOption DEFAULT_INSTANCE = new PairingOption();
        public static final int INPUT_ENCODINGS_FIELD_NUMBER = 1;
        public static final int OUTPUT_ENCODINGS_FIELD_NUMBER = 2;
        private static final Parser<PairingOption> PARSER = new AbstractParser<PairingOption>() {

            @Override
            public PairingOption parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new PairingOption(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int PREFERRED_ROLE_FIELD_NUMBER = 3;
        private static final long serialVersionUID = 0;
        private List<PairingEncoding> inputEncodings_;
        private byte memoizedIsInitialized;
        private List<PairingEncoding> outputEncodings_;
        private int preferredRole_;

        private PairingOption(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
        }

        private PairingOption() {
            this.memoizedIsInitialized = -1;
            this.inputEncodings_ = Collections.emptyList();
            this.outputEncodings_ = Collections.emptyList();
            this.preferredRole_ = 0;
        }

        
        @Override
        public Object newInstance(UnusedPrivateParameter unusedPrivateParameter) {
            return new PairingOption();
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

//        private PairingOption(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
//            this();
//            Objects.requireNonNull(extensionRegistryLite);
//            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
//            boolean z = false;
//            boolean z2 = false;
//            while (!z) {
//                try {
//                    int readTag = codedInputStream.readTag();
//                    if (readTag != 0) {
//                        if (readTag == 10) {
//                            if (!z2 || !true) {
//                                this.inputEncodings_ = new ArrayList();
//                                z2 |= true;
//                            }
//                            this.inputEncodings_.add((PairingEncoding) codedInputStream.readMessage(PairingEncoding.parser(), extensionRegistryLite));
//                        } else if (readTag == 18) {
//                            if (!z2 || !true) {
//                                this.outputEncodings_ = new ArrayList();
//                                z2 |= true;
//                            }
//                            this.outputEncodings_.add((PairingEncoding) codedInputStream.readMessage(PairingEncoding.parser(), extensionRegistryLite));
//                        } else if (readTag == 24) {
//                            this.preferredRole_ = codedInputStream.readEnum();
//                        } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
//                        }
//                    }
//                    z = true;
//                } catch (InvalidProtocolBufferException e) {
//                    throw e.setUnfinishedMessage(this);
//                } catch (IOException e2) {
//                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
//                } catch (Throwable th) {
//                    if (z2 && true) {
//                        this.inputEncodings_ = Collections.unmodifiableList(this.inputEncodings_);
//                    }
//                    if (z2 && true) {
//                        this.outputEncodings_ = Collections.unmodifiableList(this.outputEncodings_);
//                    }
//                    this.unknownFields = newBuilder.build();
//                    makeExtensionsImmutable();
//                    throw th;
//                }
//            }
//            if (z2 && true) {
//                this.inputEncodings_ = Collections.unmodifiableList(this.inputEncodings_);
//            }
//            if (z2 && true) {
//                this.outputEncodings_ = Collections.unmodifiableList(this.outputEncodings_);
//            }
//            this.unknownFields = newBuilder.build();
//            makeExtensionsImmutable();
//        }
        private PairingOption(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            int mutable_bitField0_ = 0;
            com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                    com.google.protobuf.UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        case 10: {
                            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
                                inputEncodings_ = new ArrayList<PairingEncoding>();
                                mutable_bitField0_ |= 0x00000001;
                            }
                            inputEncodings_.add(
                                    input.readMessage(PairingEncoding.parser(), extensionRegistry));
                            break;
                        }
                        case 18: {
                            if (!((mutable_bitField0_ & 0x00000002) != 0)) {
                                outputEncodings_ = new ArrayList<PairingEncoding>();
                                mutable_bitField0_ |= 0x00000002;
                            }
                            outputEncodings_.add(
                                    input.readMessage(PairingEncoding.parser(), extensionRegistry));
                            break;
                        }
                        case 24: {
                            int rawValue = input.readEnum();

                            preferredRole_ = rawValue;
                            break;
                        }
                        default: {
                            if (!parseUnknownField(
                                    input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                            break;
                        }
                    }
                }
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new com.google.protobuf.InvalidProtocolBufferException(
                        e).setUnfinishedMessage(this);
            } finally {
                if (((mutable_bitField0_ & 0x00000001) != 0)) {
                    inputEncodings_ = Collections.unmodifiableList(inputEncodings_);
                }
                if (((mutable_bitField0_ & 0x00000002) != 0)) {
                    outputEncodings_ = Collections.unmodifiableList(outputEncodings_);
                }
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }


        public static final Descriptors.Descriptor getDescriptor() {
            return Pairingmessage.internal_static_com_kunal52_pairing_PairingOption_descriptor;
        }

        
        @Override
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Pairingmessage.internal_static_com_kunal52_pairing_PairingOption_fieldAccessorTable.ensureFieldAccessorsInitialized(PairingOption.class, Builder.class);
        }

        @Override
        public List<PairingEncoding> getInputEncodingsList() {
            return this.inputEncodings_;
        }

        @Override
        public List<? extends PairingEncodingOrBuilder> getInputEncodingsOrBuilderList() {
            return this.inputEncodings_;
        }

        @Override
        public int getInputEncodingsCount() {
            return this.inputEncodings_.size();
        }

        @Override
        public PairingEncoding getInputEncodings(int i) {
            return this.inputEncodings_.get(i);
        }

        @Override
        public PairingEncodingOrBuilder getInputEncodingsOrBuilder(int i) {
            return this.inputEncodings_.get(i);
        }

        @Override
        public List<PairingEncoding> getOutputEncodingsList() {
            return this.outputEncodings_;
        }

        @Override
        public List<? extends PairingEncodingOrBuilder> getOutputEncodingsOrBuilderList() {
            return this.outputEncodings_;
        }

        @Override
        public int getOutputEncodingsCount() {
            return this.outputEncodings_.size();
        }

        @Override
        public PairingEncoding getOutputEncodings(int i) {
            return this.outputEncodings_.get(i);
        }

        @Override
        public PairingEncodingOrBuilder getOutputEncodingsOrBuilder(int i) {
            return this.outputEncodings_.get(i);
        }

        @Override
        public int getPreferredRoleValue() {
            return this.preferredRole_;
        }

        @Override
        public RoleType getPreferredRole() {
            RoleType valueOf = RoleType.valueOf(this.preferredRole_);
            return valueOf == null ? RoleType.UNRECOGNIZED : valueOf;
        }

        @Override
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            for (int i = 0; i < this.inputEncodings_.size(); i++) {
                codedOutputStream.writeMessage(1, this.inputEncodings_.get(i));
            }
            for (int i2 = 0; i2 < this.outputEncodings_.size(); i2++) {
                codedOutputStream.writeMessage(2, this.outputEncodings_.get(i2));
            }
            if (this.preferredRole_ != RoleType.ROLE_TYPE_UNKNOWN.getNumber()) {
                codedOutputStream.writeEnum(3, this.preferredRole_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.inputEncodings_.size(); i3++) {
                i2 += CodedOutputStream.computeMessageSize(1, this.inputEncodings_.get(i3));
            }
            for (int i4 = 0; i4 < this.outputEncodings_.size(); i4++) {
                i2 += CodedOutputStream.computeMessageSize(2, this.outputEncodings_.get(i4));
            }
            if (this.preferredRole_ != RoleType.ROLE_TYPE_UNKNOWN.getNumber()) {
                i2 += CodedOutputStream.computeEnumSize(3, this.preferredRole_);
            }
            int serializedSize = i2 + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof PairingOption)) {
                return super.equals(obj);
            }
            PairingOption pairingOption = (PairingOption) obj;
            if (getInputEncodingsList().equals(pairingOption.getInputEncodingsList()) && getOutputEncodingsList().equals(pairingOption.getOutputEncodingsList()) && this.preferredRole_ == pairingOption.preferredRole_ && this.unknownFields.equals(pairingOption.unknownFields)) {
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + getDescriptor().hashCode();
            if (getInputEncodingsCount() > 0) {
                hashCode = (((hashCode * 37) + 1) * 53) + getInputEncodingsList().hashCode();
            }
            if (getOutputEncodingsCount() > 0) {
                hashCode = (((hashCode * 37) + 2) * 53) + getOutputEncodingsList().hashCode();
            }
            int hashCode2 = (((((hashCode * 37) + 3) * 53) + this.preferredRole_) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static PairingOption parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static PairingOption parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static PairingOption parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static PairingOption parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static PairingOption parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static PairingOption parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static PairingOption parseFrom(InputStream inputStream) throws IOException {
            return (PairingOption) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static PairingOption parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PairingOption) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static PairingOption parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (PairingOption) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static PairingOption parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PairingOption) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static PairingOption parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (PairingOption) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static PairingOption parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PairingOption) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(PairingOption pairingOption) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(pairingOption);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        
        @Override
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements PairingOptionOrBuilder {
            private int bitField0_;
            private RepeatedFieldBuilderV3<PairingEncoding, PairingEncoding.Builder, PairingEncodingOrBuilder> inputEncodingsBuilder_;
            private List<PairingEncoding> inputEncodings_;
            private RepeatedFieldBuilderV3<PairingEncoding, PairingEncoding.Builder, PairingEncodingOrBuilder> outputEncodingsBuilder_;
            private List<PairingEncoding> outputEncodings_;
            private int preferredRole_;

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Pairingmessage.internal_static_com_kunal52_pairing_PairingOption_descriptor;
            }

            
            @Override
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Pairingmessage.internal_static_com_kunal52_pairing_PairingOption_fieldAccessorTable.ensureFieldAccessorsInitialized(PairingOption.class, Builder.class);
            }

            private Builder() {
                this.inputEncodings_ = Collections.emptyList();
                this.outputEncodings_ = Collections.emptyList();
                this.preferredRole_ = 0;
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.inputEncodings_ = Collections.emptyList();
                this.outputEncodings_ = Collections.emptyList();
                this.preferredRole_ = 0;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (PairingOption.alwaysUseFieldBuilders) {
                    getInputEncodingsFieldBuilder();
                    getOutputEncodingsFieldBuilder();
                }
            }

            @Override
            public Builder clear() {
                super.clear();
                RepeatedFieldBuilderV3<PairingEncoding, PairingEncoding.Builder, PairingEncodingOrBuilder> repeatedFieldBuilderV3 = this.inputEncodingsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.inputEncodings_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                RepeatedFieldBuilderV3<PairingEncoding, PairingEncoding.Builder, PairingEncodingOrBuilder> repeatedFieldBuilderV32 = this.outputEncodingsBuilder_;
                if (repeatedFieldBuilderV32 == null) {
                    this.outputEncodings_ = Collections.emptyList();
                    this.bitField0_ &= -3;
                } else {
                    repeatedFieldBuilderV32.clear();
                }
                this.preferredRole_ = 0;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return Pairingmessage.internal_static_com_kunal52_pairing_PairingOption_descriptor;
            }

            @Override
            public PairingOption getDefaultInstanceForType() {
                return PairingOption.getDefaultInstance();
            }

            @Override
            public PairingOption build() {
                PairingOption buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public PairingOption buildPartial() {
                PairingOption pairingOption = new PairingOption(this);
                int i = this.bitField0_;
                RepeatedFieldBuilderV3<PairingEncoding, PairingEncoding.Builder, PairingEncodingOrBuilder> repeatedFieldBuilderV3 = this.inputEncodingsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    if ((i & 1) != 0) {
                        this.inputEncodings_ = Collections.unmodifiableList(this.inputEncodings_);
                        this.bitField0_ &= -2;
                    }
                    pairingOption.inputEncodings_ = this.inputEncodings_;
                } else {
                    pairingOption.inputEncodings_ = repeatedFieldBuilderV3.build();
                }
                RepeatedFieldBuilderV3<PairingEncoding, PairingEncoding.Builder, PairingEncodingOrBuilder> repeatedFieldBuilderV32 = this.outputEncodingsBuilder_;
                if (repeatedFieldBuilderV32 == null) {
                    if ((this.bitField0_ & 2) != 0) {
                        this.outputEncodings_ = Collections.unmodifiableList(this.outputEncodings_);
                        this.bitField0_ &= -3;
                    }
                    pairingOption.outputEncodings_ = this.outputEncodings_;
                } else {
                    pairingOption.outputEncodings_ = repeatedFieldBuilderV32.build();
                }
                pairingOption.preferredRole_ = this.preferredRole_;
                onBuilt();
                return pairingOption;
            }

            @Override
            public Builder clone() {
                return (Builder) super.clone();
            }

            @Override
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override
            public Builder mergeFrom(Message message) {
                if (message instanceof PairingOption) {
                    return mergeFrom((PairingOption) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(PairingOption pairingOption) {
                if (pairingOption == PairingOption.getDefaultInstance()) {
                    return this;
                }
                RepeatedFieldBuilderV3<PairingEncoding, PairingEncoding.Builder, PairingEncodingOrBuilder> repeatedFieldBuilderV3 = null;
                if (this.inputEncodingsBuilder_ == null) {
                    if (!pairingOption.inputEncodings_.isEmpty()) {
                        if (this.inputEncodings_.isEmpty()) {
                            this.inputEncodings_ = pairingOption.inputEncodings_;
                            this.bitField0_ &= -2;
                        } else {
                            ensureInputEncodingsIsMutable();
                            this.inputEncodings_.addAll(pairingOption.inputEncodings_);
                        }
                        onChanged();
                    }
                } else if (!pairingOption.inputEncodings_.isEmpty()) {
                    if (this.inputEncodingsBuilder_.isEmpty()) {
                        this.inputEncodingsBuilder_.dispose();
                        this.inputEncodingsBuilder_ = null;
                        this.inputEncodings_ = pairingOption.inputEncodings_;
                        this.bitField0_ &= -2;
                        this.inputEncodingsBuilder_ = PairingOption.alwaysUseFieldBuilders ? getInputEncodingsFieldBuilder() : null;
                    } else {
                        this.inputEncodingsBuilder_.addAllMessages(pairingOption.inputEncodings_);
                    }
                }
                if (this.outputEncodingsBuilder_ == null) {
                    if (!pairingOption.outputEncodings_.isEmpty()) {
                        if (this.outputEncodings_.isEmpty()) {
                            this.outputEncodings_ = pairingOption.outputEncodings_;
                            this.bitField0_ &= -3;
                        } else {
                            ensureOutputEncodingsIsMutable();
                            this.outputEncodings_.addAll(pairingOption.outputEncodings_);
                        }
                        onChanged();
                    }
                } else if (!pairingOption.outputEncodings_.isEmpty()) {
                    if (this.outputEncodingsBuilder_.isEmpty()) {
                        this.outputEncodingsBuilder_.dispose();
                        this.outputEncodingsBuilder_ = null;
                        this.outputEncodings_ = pairingOption.outputEncodings_;
                        this.bitField0_ &= -3;
                        if (PairingOption.alwaysUseFieldBuilders) {
                            repeatedFieldBuilderV3 = getOutputEncodingsFieldBuilder();
                        }
                        this.outputEncodingsBuilder_ = repeatedFieldBuilderV3;
                    } else {
                        this.outputEncodingsBuilder_.addAllMessages(pairingOption.outputEncodings_);
                    }
                }
                if (pairingOption.preferredRole_ != 0) {
                    setPreferredRoleValue(pairingOption.getPreferredRoleValue());
                }
                mergeUnknownFields(pairingOption.unknownFields);
                onChanged();
                return this;
            }

            /* CODE  */
            @Override
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                PairingOption pairingOption = null;
                try {
                    pairingOption = (PairingOption) PairingOption.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                } catch (InvalidProtocolBufferException e) {
                    pairingOption = (PairingOption) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (pairingOption != null) {
                        mergeFrom(pairingOption);
                    }
                }
                return this;
            }

            private void ensureInputEncodingsIsMutable() {
                if ((this.bitField0_ & 1) == 0) {
                    this.inputEncodings_ = new ArrayList(this.inputEncodings_);
                    this.bitField0_ |= 1;
                }
            }

            @Override
            public List<PairingEncoding> getInputEncodingsList() {
                RepeatedFieldBuilderV3<PairingEncoding, PairingEncoding.Builder, PairingEncodingOrBuilder> repeatedFieldBuilderV3 = this.inputEncodingsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.inputEncodings_);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            @Override
            public int getInputEncodingsCount() {
                RepeatedFieldBuilderV3<PairingEncoding, PairingEncoding.Builder, PairingEncodingOrBuilder> repeatedFieldBuilderV3 = this.inputEncodingsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.inputEncodings_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            @Override
            public PairingEncoding getInputEncodings(int i) {
                RepeatedFieldBuilderV3<PairingEncoding, PairingEncoding.Builder, PairingEncodingOrBuilder> repeatedFieldBuilderV3 = this.inputEncodingsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.inputEncodings_.get(i);
                }
                return repeatedFieldBuilderV3.getMessage(i);
            }

            public Builder setInputEncodings(int i, PairingEncoding pairingEncoding) {
                RepeatedFieldBuilderV3<PairingEncoding, PairingEncoding.Builder, PairingEncodingOrBuilder> repeatedFieldBuilderV3 = this.inputEncodingsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    Objects.requireNonNull(pairingEncoding);
                    ensureInputEncodingsIsMutable();
                    this.inputEncodings_.set(i, pairingEncoding);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, pairingEncoding);
                }
                return this;
            }

            public Builder setInputEncodings(int i, PairingEncoding.Builder builder) {
                RepeatedFieldBuilderV3<PairingEncoding, PairingEncoding.Builder, PairingEncodingOrBuilder> repeatedFieldBuilderV3 = this.inputEncodingsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureInputEncodingsIsMutable();
                    this.inputEncodings_.set(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder addInputEncodings(PairingEncoding pairingEncoding) {
                RepeatedFieldBuilderV3<PairingEncoding, PairingEncoding.Builder, PairingEncodingOrBuilder> repeatedFieldBuilderV3 = this.inputEncodingsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    Objects.requireNonNull(pairingEncoding);
                    ensureInputEncodingsIsMutable();
                    this.inputEncodings_.add(pairingEncoding);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(pairingEncoding);
                }
                return this;
            }

            public Builder addInputEncodings(int i, PairingEncoding pairingEncoding) {
                RepeatedFieldBuilderV3<PairingEncoding, PairingEncoding.Builder, PairingEncodingOrBuilder> repeatedFieldBuilderV3 = this.inputEncodingsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    Objects.requireNonNull(pairingEncoding);
                    ensureInputEncodingsIsMutable();
                    this.inputEncodings_.add(i, pairingEncoding);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, pairingEncoding);
                }
                return this;
            }

            public Builder addInputEncodings(PairingEncoding.Builder builder) {
                RepeatedFieldBuilderV3<PairingEncoding, PairingEncoding.Builder, PairingEncodingOrBuilder> repeatedFieldBuilderV3 = this.inputEncodingsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureInputEncodingsIsMutable();
                    this.inputEncodings_.add(builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.build());
                }
                return this;
            }

            public Builder addInputEncodings(int i, PairingEncoding.Builder builder) {
                RepeatedFieldBuilderV3<PairingEncoding, PairingEncoding.Builder, PairingEncodingOrBuilder> repeatedFieldBuilderV3 = this.inputEncodingsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureInputEncodingsIsMutable();
                    this.inputEncodings_.add(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addAllInputEncodings(Iterable<? extends PairingEncoding> iterable) {
                RepeatedFieldBuilderV3<PairingEncoding, PairingEncoding.Builder, PairingEncodingOrBuilder> repeatedFieldBuilderV3 = this.inputEncodingsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureInputEncodingsIsMutable();
                    AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.inputEncodings_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearInputEncodings() {
                RepeatedFieldBuilderV3<PairingEncoding, PairingEncoding.Builder, PairingEncodingOrBuilder> repeatedFieldBuilderV3 = this.inputEncodingsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.inputEncodings_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Builder removeInputEncodings(int i) {
                RepeatedFieldBuilderV3<PairingEncoding, PairingEncoding.Builder, PairingEncodingOrBuilder> repeatedFieldBuilderV3 = this.inputEncodingsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureInputEncodingsIsMutable();
                    this.inputEncodings_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public PairingEncoding.Builder getInputEncodingsBuilder(int i) {
                return getInputEncodingsFieldBuilder().getBuilder(i);
            }

            @Override
            public PairingEncodingOrBuilder getInputEncodingsOrBuilder(int i) {
                RepeatedFieldBuilderV3<PairingEncoding, PairingEncoding.Builder, PairingEncodingOrBuilder> repeatedFieldBuilderV3 = this.inputEncodingsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.inputEncodings_.get(i);
                }
                return repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override
            public List<? extends PairingEncodingOrBuilder> getInputEncodingsOrBuilderList() {
                RepeatedFieldBuilderV3<PairingEncoding, PairingEncoding.Builder, PairingEncodingOrBuilder> repeatedFieldBuilderV3 = this.inputEncodingsBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.inputEncodings_);
            }

            public PairingEncoding.Builder addInputEncodingsBuilder() {
                return getInputEncodingsFieldBuilder().addBuilder(PairingEncoding.getDefaultInstance());
            }

            public PairingEncoding.Builder addInputEncodingsBuilder(int i) {
                return getInputEncodingsFieldBuilder().addBuilder(i, PairingEncoding.getDefaultInstance());
            }

            public List<PairingEncoding.Builder> getInputEncodingsBuilderList() {
                return getInputEncodingsFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<PairingEncoding, PairingEncoding.Builder, PairingEncodingOrBuilder> getInputEncodingsFieldBuilder() {
                if (this.inputEncodingsBuilder_ == null) {
                    List<PairingEncoding> list = this.inputEncodings_;
                    boolean z = true;
                    if ((this.bitField0_ & 1) == 0) {
                        z = false;
                    }
                    this.inputEncodingsBuilder_ = new RepeatedFieldBuilderV3<>(list, z, getParentForChildren(), isClean());
                    this.inputEncodings_ = null;
                }
                return this.inputEncodingsBuilder_;
            }

            private void ensureOutputEncodingsIsMutable() {
                if ((this.bitField0_ & 2) == 0) {
                    this.outputEncodings_ = new ArrayList(this.outputEncodings_);
                    this.bitField0_ |= 2;
                }
            }

            @Override
            public List<PairingEncoding> getOutputEncodingsList() {
                RepeatedFieldBuilderV3<PairingEncoding, PairingEncoding.Builder, PairingEncodingOrBuilder> repeatedFieldBuilderV3 = this.outputEncodingsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.outputEncodings_);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            @Override
            public int getOutputEncodingsCount() {
                RepeatedFieldBuilderV3<PairingEncoding, PairingEncoding.Builder, PairingEncodingOrBuilder> repeatedFieldBuilderV3 = this.outputEncodingsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.outputEncodings_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            @Override
            public PairingEncoding getOutputEncodings(int i) {
                RepeatedFieldBuilderV3<PairingEncoding, PairingEncoding.Builder, PairingEncodingOrBuilder> repeatedFieldBuilderV3 = this.outputEncodingsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.outputEncodings_.get(i);
                }
                return repeatedFieldBuilderV3.getMessage(i);
            }

            public Builder setOutputEncodings(int i, PairingEncoding pairingEncoding) {
                RepeatedFieldBuilderV3<PairingEncoding, PairingEncoding.Builder, PairingEncodingOrBuilder> repeatedFieldBuilderV3 = this.outputEncodingsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    Objects.requireNonNull(pairingEncoding);
                    ensureOutputEncodingsIsMutable();
                    this.outputEncodings_.set(i, pairingEncoding);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, pairingEncoding);
                }
                return this;
            }

            public Builder setOutputEncodings(int i, PairingEncoding.Builder builder) {
                RepeatedFieldBuilderV3<PairingEncoding, PairingEncoding.Builder, PairingEncodingOrBuilder> repeatedFieldBuilderV3 = this.outputEncodingsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureOutputEncodingsIsMutable();
                    this.outputEncodings_.set(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder addOutputEncodings(PairingEncoding pairingEncoding) {
                RepeatedFieldBuilderV3<PairingEncoding, PairingEncoding.Builder, PairingEncodingOrBuilder> repeatedFieldBuilderV3 = this.outputEncodingsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    Objects.requireNonNull(pairingEncoding);
                    ensureOutputEncodingsIsMutable();
                    this.outputEncodings_.add(pairingEncoding);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(pairingEncoding);
                }
                return this;
            }

            public Builder addOutputEncodings(int i, PairingEncoding pairingEncoding) {
                RepeatedFieldBuilderV3<PairingEncoding, PairingEncoding.Builder, PairingEncodingOrBuilder> repeatedFieldBuilderV3 = this.outputEncodingsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    Objects.requireNonNull(pairingEncoding);
                    ensureOutputEncodingsIsMutable();
                    this.outputEncodings_.add(i, pairingEncoding);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, pairingEncoding);
                }
                return this;
            }

            public Builder addOutputEncodings(PairingEncoding.Builder builder) {
                RepeatedFieldBuilderV3<PairingEncoding, PairingEncoding.Builder, PairingEncodingOrBuilder> repeatedFieldBuilderV3 = this.outputEncodingsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureOutputEncodingsIsMutable();
                    this.outputEncodings_.add(builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.build());
                }
                return this;
            }

            public Builder addOutputEncodings(int i, PairingEncoding.Builder builder) {
                RepeatedFieldBuilderV3<PairingEncoding, PairingEncoding.Builder, PairingEncodingOrBuilder> repeatedFieldBuilderV3 = this.outputEncodingsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureOutputEncodingsIsMutable();
                    this.outputEncodings_.add(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addAllOutputEncodings(Iterable<? extends PairingEncoding> iterable) {
                RepeatedFieldBuilderV3<PairingEncoding, PairingEncoding.Builder, PairingEncodingOrBuilder> repeatedFieldBuilderV3 = this.outputEncodingsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureOutputEncodingsIsMutable();
                    AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.outputEncodings_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearOutputEncodings() {
                RepeatedFieldBuilderV3<PairingEncoding, PairingEncoding.Builder, PairingEncodingOrBuilder> repeatedFieldBuilderV3 = this.outputEncodingsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.outputEncodings_ = Collections.emptyList();
                    this.bitField0_ &= -3;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Builder removeOutputEncodings(int i) {
                RepeatedFieldBuilderV3<PairingEncoding, PairingEncoding.Builder, PairingEncodingOrBuilder> repeatedFieldBuilderV3 = this.outputEncodingsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureOutputEncodingsIsMutable();
                    this.outputEncodings_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public PairingEncoding.Builder getOutputEncodingsBuilder(int i) {
                return getOutputEncodingsFieldBuilder().getBuilder(i);
            }

            @Override
            public PairingEncodingOrBuilder getOutputEncodingsOrBuilder(int i) {
                RepeatedFieldBuilderV3<PairingEncoding, PairingEncoding.Builder, PairingEncodingOrBuilder> repeatedFieldBuilderV3 = this.outputEncodingsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.outputEncodings_.get(i);
                }
                return repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override
            public List<? extends PairingEncodingOrBuilder> getOutputEncodingsOrBuilderList() {
                RepeatedFieldBuilderV3<PairingEncoding, PairingEncoding.Builder, PairingEncodingOrBuilder> repeatedFieldBuilderV3 = this.outputEncodingsBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.outputEncodings_);
            }

            public PairingEncoding.Builder addOutputEncodingsBuilder() {
                return getOutputEncodingsFieldBuilder().addBuilder(PairingEncoding.getDefaultInstance());
            }

            public PairingEncoding.Builder addOutputEncodingsBuilder(int i) {
                return getOutputEncodingsFieldBuilder().addBuilder(i, PairingEncoding.getDefaultInstance());
            }

            public List<PairingEncoding.Builder> getOutputEncodingsBuilderList() {
                return getOutputEncodingsFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<PairingEncoding, PairingEncoding.Builder, PairingEncodingOrBuilder> getOutputEncodingsFieldBuilder() {
                if (this.outputEncodingsBuilder_ == null) {
                    this.outputEncodingsBuilder_ = new RepeatedFieldBuilderV3<>(this.outputEncodings_, (this.bitField0_ & 2) != 0, getParentForChildren(), isClean());
                    this.outputEncodings_ = null;
                }
                return this.outputEncodingsBuilder_;
            }

            @Override
            public int getPreferredRoleValue() {
                return this.preferredRole_;
            }

            public Builder setPreferredRoleValue(int i) {
                this.preferredRole_ = i;
                onChanged();
                return this;
            }

            @Override
            public RoleType getPreferredRole() {
                RoleType valueOf = RoleType.valueOf(this.preferredRole_);
                return valueOf == null ? RoleType.UNRECOGNIZED : valueOf;
            }

            public Builder setPreferredRole(RoleType roleType) {
                Objects.requireNonNull(roleType);
                this.preferredRole_ = roleType.getNumber();
                onChanged();
                return this;
            }

            public Builder clearPreferredRole() {
                this.preferredRole_ = 0;
                onChanged();
                return this;
            }

            @Override
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }

        public static PairingOption getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<PairingOption> parser() {
            return PARSER;
        }

        @Override
        public Parser<PairingOption> getParserForType() {
            return PARSER;
        }

        @Override
        public PairingOption getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static final class PairingConfiguration extends GeneratedMessageV3 implements PairingConfigurationOrBuilder {
        public static final int CLIENT_ROLE_FIELD_NUMBER = 2;
        private static final PairingConfiguration DEFAULT_INSTANCE = new PairingConfiguration();
        public static final int ENCODING_FIELD_NUMBER = 1;
        private static final Parser<PairingConfiguration> PARSER = new AbstractParser<PairingConfiguration>() {

            @Override
            public PairingConfiguration parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new PairingConfiguration(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private int clientRole_;
        private PairingEncoding encoding_;
        private byte memoizedIsInitialized;

        private PairingConfiguration(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
        }

        private PairingConfiguration() {
            this.memoizedIsInitialized = -1;
            this.clientRole_ = 0;
        }

        
        @Override
        public Object newInstance(UnusedPrivateParameter unusedPrivateParameter) {
            return new PairingConfiguration();
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

//        private PairingConfiguration1(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
//            this();
//            Objects.requireNonNull(extensionRegistryLite);
//            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
//            boolean z = false;
//            while (!z) {
//                try {
//                    int readTag = codedInputStream.readTag();
//                    if (readTag != 0) {
//                        if (readTag == 10) {
//                            PairingEncoding.Builder builder = null;
//                            PairingEncoding pairingEncoding = this.encoding_;
//                            builder = pairingEncoding != null ? pairingEncoding.toBuilder() : builder;
//                            PairingEncoding pairingEncoding2 = (PairingEncoding) codedInputStream.readMessage(PairingEncoding.parser(), extensionRegistryLite);
//                            this.encoding_ = pairingEncoding2;
//                            if (builder != null) {
//                                builder.mergeFrom(pairingEncoding2);
//                                this.encoding_ = builder.buildPartial();
//                            }
//                        } else if (readTag == 16) {
//                            this.clientRole_ = codedInputStream.readEnum();
//                        } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
//                        }
//                    }
//                    z = true;
//                } catch (InvalidProtocolBufferException e) {
//                    throw e.setUnfinishedMessage(this);
//                } catch (IOException e2) {
//                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
//                } catch (Throwable th) {
//                    this.unknownFields = newBuilder.build();
//                    makeExtensionsImmutable();
//                    throw th;
//                }
//            }
//            this.unknownFields = newBuilder.build();
//            makeExtensionsImmutable();
//        }
        private PairingConfiguration(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                    com.google.protobuf.UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        case 10: {
                            PairingEncoding.Builder subBuilder = null;
                            if (encoding_ != null) {
                                subBuilder = encoding_.toBuilder();
                            }
                            encoding_ = input.readMessage(PairingEncoding.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(encoding_);
                                encoding_ = subBuilder.buildPartial();
                            }

                            break;
                        }
                        case 16: {
                            int rawValue = input.readEnum();

                            clientRole_ = rawValue;
                            break;
                        }
                        default: {
                            if (!parseUnknownField(
                                    input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                            break;
                        }
                    }
                }
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new com.google.protobuf.InvalidProtocolBufferException(
                        e).setUnfinishedMessage(this);
            } finally {
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }


        public static final Descriptors.Descriptor getDescriptor() {
            return Pairingmessage.internal_static_com_kunal52_pairing_PairingConfiguration_descriptor;
        }

        
        @Override
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Pairingmessage.internal_static_com_kunal52_pairing_PairingConfiguration_fieldAccessorTable.ensureFieldAccessorsInitialized(PairingConfiguration.class, Builder.class);
        }

        @Override
        public boolean hasEncoding() {
            return this.encoding_ != null;
        }

        @Override
        public PairingEncoding getEncoding() {
            PairingEncoding pairingEncoding = this.encoding_;
            return pairingEncoding == null ? PairingEncoding.getDefaultInstance() : pairingEncoding;
        }

        @Override
        public PairingEncodingOrBuilder getEncodingOrBuilder() {
            return getEncoding();
        }

        @Override
        public int getClientRoleValue() {
            return this.clientRole_;
        }

        @Override
        public RoleType getClientRole() {
            RoleType valueOf = RoleType.valueOf(this.clientRole_);
            return valueOf == null ? RoleType.UNRECOGNIZED : valueOf;
        }

        @Override
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.encoding_ != null) {
                codedOutputStream.writeMessage(1, getEncoding());
            }
            if (this.clientRole_ != RoleType.ROLE_TYPE_UNKNOWN.getNumber()) {
                codedOutputStream.writeEnum(2, this.clientRole_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.encoding_ != null) {
                i2 = 0 + CodedOutputStream.computeMessageSize(1, getEncoding());
            }
            if (this.clientRole_ != RoleType.ROLE_TYPE_UNKNOWN.getNumber()) {
                i2 += CodedOutputStream.computeEnumSize(2, this.clientRole_);
            }
            int serializedSize = i2 + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof PairingConfiguration)) {
                return super.equals(obj);
            }
            PairingConfiguration pairingConfiguration = (PairingConfiguration) obj;
            if (hasEncoding() != pairingConfiguration.hasEncoding()) {
                return false;
            }
            if ((!hasEncoding() || getEncoding().equals(pairingConfiguration.getEncoding())) && this.clientRole_ == pairingConfiguration.clientRole_ && this.unknownFields.equals(pairingConfiguration.unknownFields)) {
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + getDescriptor().hashCode();
            if (hasEncoding()) {
                hashCode = (((hashCode * 37) + 1) * 53) + getEncoding().hashCode();
            }
            int hashCode2 = (((((hashCode * 37) + 2) * 53) + this.clientRole_) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static PairingConfiguration parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static PairingConfiguration parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static PairingConfiguration parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static PairingConfiguration parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static PairingConfiguration parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static PairingConfiguration parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static PairingConfiguration parseFrom(InputStream inputStream) throws IOException {
            return (PairingConfiguration) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static PairingConfiguration parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PairingConfiguration) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static PairingConfiguration parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (PairingConfiguration) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static PairingConfiguration parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PairingConfiguration) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static PairingConfiguration parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (PairingConfiguration) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static PairingConfiguration parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PairingConfiguration) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(PairingConfiguration pairingConfiguration) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(pairingConfiguration);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        
        @Override
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements PairingConfigurationOrBuilder {
            private int clientRole_;
            private SingleFieldBuilderV3<PairingEncoding, PairingEncoding.Builder, PairingEncodingOrBuilder> encodingBuilder_;
            private PairingEncoding encoding_;

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Pairingmessage.internal_static_com_kunal52_pairing_PairingConfiguration_descriptor;
            }

            
            @Override
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Pairingmessage.internal_static_com_kunal52_pairing_PairingConfiguration_fieldAccessorTable.ensureFieldAccessorsInitialized(PairingConfiguration.class, Builder.class);
            }

            private Builder() {
                this.clientRole_ = 0;
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.clientRole_ = 0;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = PairingConfiguration.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                if (this.encodingBuilder_ == null) {
                    this.encoding_ = null;
                } else {
                    this.encoding_ = null;
                    this.encodingBuilder_ = null;
                }
                this.clientRole_ = 0;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return Pairingmessage.internal_static_com_kunal52_pairing_PairingConfiguration_descriptor;
            }

            @Override
            public PairingConfiguration getDefaultInstanceForType() {
                return PairingConfiguration.getDefaultInstance();
            }

            @Override
            public PairingConfiguration build() {
                PairingConfiguration buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public PairingConfiguration buildPartial() {
                PairingConfiguration pairingConfiguration = new PairingConfiguration(this);
                SingleFieldBuilderV3<PairingEncoding, PairingEncoding.Builder, PairingEncodingOrBuilder> singleFieldBuilderV3 = this.encodingBuilder_;
                if (singleFieldBuilderV3 == null) {
                    pairingConfiguration.encoding_ = this.encoding_;
                } else {
                    pairingConfiguration.encoding_ = singleFieldBuilderV3.build();
                }
                pairingConfiguration.clientRole_ = this.clientRole_;
                onBuilt();
                return pairingConfiguration;
            }

            @Override
            public Builder clone() {
                return (Builder) super.clone();
            }

            @Override
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override
            public Builder mergeFrom(Message message) {
                if (message instanceof PairingConfiguration) {
                    return mergeFrom((PairingConfiguration) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(PairingConfiguration pairingConfiguration) {
                if (pairingConfiguration == PairingConfiguration.getDefaultInstance()) {
                    return this;
                }
                if (pairingConfiguration.hasEncoding()) {
                    mergeEncoding(pairingConfiguration.getEncoding());
                }
                if (pairingConfiguration.clientRole_ != 0) {
                    setClientRoleValue(pairingConfiguration.getClientRoleValue());
                }
                mergeUnknownFields(pairingConfiguration.unknownFields);
                onChanged();
                return this;
            }

            /* CODE  */
            @Override
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                PairingConfiguration pairingConfiguration = null;
                try {
                    pairingConfiguration = (PairingConfiguration) PairingConfiguration.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                } catch (InvalidProtocolBufferException e) {
                    pairingConfiguration = (PairingConfiguration) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (pairingConfiguration != null) {
                        mergeFrom(pairingConfiguration);
                    }
                }
                return this;
            }

            @Override
            public boolean hasEncoding() {
                return (this.encodingBuilder_ == null && this.encoding_ == null) ? false : true;
            }

            @Override
            public PairingEncoding getEncoding() {
                SingleFieldBuilderV3<PairingEncoding, PairingEncoding.Builder, PairingEncodingOrBuilder> singleFieldBuilderV3 = this.encodingBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                PairingEncoding pairingEncoding = this.encoding_;
                return pairingEncoding == null ? PairingEncoding.getDefaultInstance() : pairingEncoding;
            }

            public Builder setEncoding(PairingEncoding pairingEncoding) {
                SingleFieldBuilderV3<PairingEncoding, PairingEncoding.Builder, PairingEncodingOrBuilder> singleFieldBuilderV3 = this.encodingBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Objects.requireNonNull(pairingEncoding);
                    this.encoding_ = pairingEncoding;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(pairingEncoding);
                }
                return this;
            }

            public Builder setEncoding(PairingEncoding.Builder builder) {
                SingleFieldBuilderV3<PairingEncoding, PairingEncoding.Builder, PairingEncodingOrBuilder> singleFieldBuilderV3 = this.encodingBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.encoding_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeEncoding(PairingEncoding pairingEncoding) {
                SingleFieldBuilderV3<PairingEncoding, PairingEncoding.Builder, PairingEncodingOrBuilder> singleFieldBuilderV3 = this.encodingBuilder_;
                if (singleFieldBuilderV3 == null) {
                    PairingEncoding pairingEncoding2 = this.encoding_;
                    if (pairingEncoding2 != null) {
                        this.encoding_ = PairingEncoding.newBuilder(pairingEncoding2).mergeFrom(pairingEncoding).buildPartial();
                    } else {
                        this.encoding_ = pairingEncoding;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(pairingEncoding);
                }
                return this;
            }

            public Builder clearEncoding() {
                if (this.encodingBuilder_ == null) {
                    this.encoding_ = null;
                    onChanged();
                } else {
                    this.encoding_ = null;
                    this.encodingBuilder_ = null;
                }
                return this;
            }

            public PairingEncoding.Builder getEncodingBuilder() {
                onChanged();
                return getEncodingFieldBuilder().getBuilder();
            }

            @Override
            public PairingEncodingOrBuilder getEncodingOrBuilder() {
                SingleFieldBuilderV3<PairingEncoding, PairingEncoding.Builder, PairingEncodingOrBuilder> singleFieldBuilderV3 = this.encodingBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                PairingEncoding pairingEncoding = this.encoding_;
                return pairingEncoding == null ? PairingEncoding.getDefaultInstance() : pairingEncoding;
            }

            private SingleFieldBuilderV3<PairingEncoding, PairingEncoding.Builder, PairingEncodingOrBuilder> getEncodingFieldBuilder() {
                if (this.encodingBuilder_ == null) {
                    this.encodingBuilder_ = new SingleFieldBuilderV3<>(getEncoding(), getParentForChildren(), isClean());
                    this.encoding_ = null;
                }
                return this.encodingBuilder_;
            }

            @Override
            public int getClientRoleValue() {
                return this.clientRole_;
            }

            public Builder setClientRoleValue(int i) {
                this.clientRole_ = i;
                onChanged();
                return this;
            }

            @Override
            public RoleType getClientRole() {
                RoleType valueOf = RoleType.valueOf(this.clientRole_);
                return valueOf == null ? RoleType.UNRECOGNIZED : valueOf;
            }

            public Builder setClientRole(RoleType roleType) {
                Objects.requireNonNull(roleType);
                this.clientRole_ = roleType.getNumber();
                onChanged();
                return this;
            }

            public Builder clearClientRole() {
                this.clientRole_ = 0;
                onChanged();
                return this;
            }

            @Override
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }

        public static PairingConfiguration getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<PairingConfiguration> parser() {
            return PARSER;
        }

        @Override
        public Parser<PairingConfiguration> getParserForType() {
            return PARSER;
        }

        @Override
        public PairingConfiguration getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static final class PairingConfigurationAck extends GeneratedMessageV3 implements PairingConfigurationAckOrBuilder {
        private static final PairingConfigurationAck DEFAULT_INSTANCE = new PairingConfigurationAck();
        private static final Parser<PairingConfigurationAck> PARSER = new AbstractParser<PairingConfigurationAck>() {

            @Override
            public PairingConfigurationAck parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new PairingConfigurationAck(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;

        private PairingConfigurationAck(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
        }

        private PairingConfigurationAck() {
            this.memoizedIsInitialized = -1;
        }

        
        @Override
        public Object newInstance(UnusedPrivateParameter unusedPrivateParameter) {
            return new PairingConfigurationAck();
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

//        private PairingConfigurationAck(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
//            this();
//            Objects.requireNonNull(extensionRegistryLite);
//            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
//            boolean z = false;
//            while (!z) {
//                try {
//                    int readTag = codedInputStream.readTag();
//                    if (readTag == 0 || !parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
//                        z = true;
//                    }
//                } catch (InvalidProtocolBufferException e) {
//                    throw e.setUnfinishedMessage(this);
//                } catch (IOException e2) {
//                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
//                } catch (Throwable th) {
//                    this.unknownFields = newBuilder.build();
//                    makeExtensionsImmutable();
//                    throw th;
//                }
//            }
//            this.unknownFields = newBuilder.build();
//            makeExtensionsImmutable();
//        }
        private PairingConfigurationAck(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                    com.google.protobuf.UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        default: {
                            if (!parseUnknownField(
                                    input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                            break;
                        }
                    }
                }
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new com.google.protobuf.InvalidProtocolBufferException(
                        e).setUnfinishedMessage(this);
            } finally {
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }


        public static final Descriptors.Descriptor getDescriptor() {
            return Pairingmessage.internal_static_com_kunal52_pairing_PairingConfigurationAck_descriptor;
        }

        
        @Override
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Pairingmessage.internal_static_com_kunal52_pairing_PairingConfigurationAck_fieldAccessorTable.ensureFieldAccessorsInitialized(PairingConfigurationAck.class, Builder.class);
        }

        @Override
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int serializedSize = this.unknownFields.getSerializedSize() + 0;
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof PairingConfigurationAck)) {
                return super.equals(obj);
            }
            if (!this.unknownFields.equals(((PairingConfigurationAck) obj).unknownFields)) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((779 + getDescriptor().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static PairingConfigurationAck parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static PairingConfigurationAck parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static PairingConfigurationAck parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static PairingConfigurationAck parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static PairingConfigurationAck parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static PairingConfigurationAck parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static PairingConfigurationAck parseFrom(InputStream inputStream) throws IOException {
            return (PairingConfigurationAck) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static PairingConfigurationAck parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PairingConfigurationAck) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static PairingConfigurationAck parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (PairingConfigurationAck) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static PairingConfigurationAck parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PairingConfigurationAck) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static PairingConfigurationAck parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (PairingConfigurationAck) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static PairingConfigurationAck parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PairingConfigurationAck) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(PairingConfigurationAck pairingConfigurationAck) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(pairingConfigurationAck);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        
        @Override
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements PairingConfigurationAckOrBuilder {
            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Pairingmessage.internal_static_com_kunal52_pairing_PairingConfigurationAck_descriptor;
            }

            
            @Override
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Pairingmessage.internal_static_com_kunal52_pairing_PairingConfigurationAck_fieldAccessorTable.ensureFieldAccessorsInitialized(PairingConfigurationAck.class, Builder.class);
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = PairingConfigurationAck.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return Pairingmessage.internal_static_com_kunal52_pairing_PairingConfigurationAck_descriptor;
            }

            @Override
            public PairingConfigurationAck getDefaultInstanceForType() {
                return PairingConfigurationAck.getDefaultInstance();
            }

            @Override
            public PairingConfigurationAck build() {
                PairingConfigurationAck buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public PairingConfigurationAck buildPartial() {
                PairingConfigurationAck pairingConfigurationAck = new PairingConfigurationAck(this);
                onBuilt();
                return pairingConfigurationAck;
            }

            @Override
            public Builder clone() {
                return (Builder) super.clone();
            }

            @Override
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override
            public Builder mergeFrom(Message message) {
                if (message instanceof PairingConfigurationAck) {
                    return mergeFrom((PairingConfigurationAck) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(PairingConfigurationAck pairingConfigurationAck) {
                if (pairingConfigurationAck == PairingConfigurationAck.getDefaultInstance()) {
                    return this;
                }
                mergeUnknownFields(pairingConfigurationAck.unknownFields);
                onChanged();
                return this;
            }

            /* CODE */
            @Override
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                PairingConfigurationAck pairingConfigurationAck = null;
                try {
                    pairingConfigurationAck = (PairingConfigurationAck) PairingConfigurationAck.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                } catch (InvalidProtocolBufferException e) {
                    pairingConfigurationAck = (PairingConfigurationAck) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (pairingConfigurationAck != null) {
                        mergeFrom(pairingConfigurationAck);
                    }
                }
                return this;
            }

            @Override
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }

        public static PairingConfigurationAck getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<PairingConfigurationAck> parser() {
            return PARSER;
        }

        @Override
        public Parser<PairingConfigurationAck> getParserForType() {
            return PARSER;
        }

        @Override
        public PairingConfigurationAck getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static final class PairingSecret extends GeneratedMessageV3 implements PairingSecretOrBuilder {
        private static final PairingSecret DEFAULT_INSTANCE = new PairingSecret();
        private static final Parser<PairingSecret> PARSER = new AbstractParser<PairingSecret>() {

            @Override
            public PairingSecret parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new PairingSecret(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int SECRET_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private ByteString secret_;

        private PairingSecret(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
        }

        private PairingSecret() {
            this.memoizedIsInitialized = -1;
            this.secret_ = ByteString.EMPTY;
        }

        
        @Override
        public Object newInstance(UnusedPrivateParameter unusedPrivateParameter) {
            return new PairingSecret();
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

//        private PairingSecret(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
//            this();
//            Objects.requireNonNull(extensionRegistryLite);
//            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
//            boolean z = false;
//            while (!z) {
//                try {
//                    int readTag = codedInputStream.readTag();
//                    if (readTag != 0) {
//                        if (readTag == 10) {
//                            this.secret_ = codedInputStream.readBytes();
//                        } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
//                        }
//                    }
//                    z = true;
//                } catch (InvalidProtocolBufferException e) {
//                    throw e.setUnfinishedMessage(this);
//                } catch (IOException e2) {
//                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
//                } catch (Throwable th) {
//                    this.unknownFields = newBuilder.build();
//                    makeExtensionsImmutable();
//                    throw th;
//                }
//            }
//            this.unknownFields = newBuilder.build();
//            makeExtensionsImmutable();
//        }
        private PairingSecret(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                    com.google.protobuf.UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        case 10: {

                            secret_ = input.readBytes();
                            break;
                        }
                        default: {
                            if (!parseUnknownField(
                                    input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                            break;
                        }
                    }
                }
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new com.google.protobuf.InvalidProtocolBufferException(
                        e).setUnfinishedMessage(this);
            } finally {
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }


        public static final Descriptors.Descriptor getDescriptor() {
            return Pairingmessage.internal_static_com_kunal52_pairing_PairingSecret_descriptor;
        }

        
        @Override
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Pairingmessage.internal_static_com_kunal52_pairing_PairingSecret_fieldAccessorTable.ensureFieldAccessorsInitialized(PairingSecret.class, Builder.class);
        }

        @Override
        public ByteString getSecret() {
            return this.secret_;
        }

        @Override
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.secret_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.secret_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.secret_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeBytesSize(1, this.secret_);
            }
            int serializedSize = i2 + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof PairingSecret)) {
                return super.equals(obj);
            }
            PairingSecret pairingSecret = (PairingSecret) obj;
            if (getSecret().equals(pairingSecret.getSecret()) && this.unknownFields.equals(pairingSecret.unknownFields)) {
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getSecret().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static PairingSecret parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static PairingSecret parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static PairingSecret parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static PairingSecret parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static PairingSecret parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static PairingSecret parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static PairingSecret parseFrom(InputStream inputStream) throws IOException {
            return (PairingSecret) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static PairingSecret parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PairingSecret) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static PairingSecret parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (PairingSecret) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static PairingSecret parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PairingSecret) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static PairingSecret parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (PairingSecret) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static PairingSecret parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PairingSecret) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(PairingSecret pairingSecret) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(pairingSecret);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        
        @Override
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements PairingSecretOrBuilder {
            private ByteString secret_;

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Pairingmessage.internal_static_com_kunal52_pairing_PairingSecret_descriptor;
            }

            
            @Override
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Pairingmessage.internal_static_com_kunal52_pairing_PairingSecret_fieldAccessorTable.ensureFieldAccessorsInitialized(PairingSecret.class, Builder.class);
            }

            private Builder() {
                this.secret_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.secret_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = PairingSecret.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.secret_ = ByteString.EMPTY;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return Pairingmessage.internal_static_com_kunal52_pairing_PairingSecret_descriptor;
            }

            @Override
            public PairingSecret getDefaultInstanceForType() {
                return PairingSecret.getDefaultInstance();
            }

            @Override
            public PairingSecret build() {
                PairingSecret buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public PairingSecret buildPartial() {
                PairingSecret pairingSecret = new PairingSecret(this);
                pairingSecret.secret_ = this.secret_;
                onBuilt();
                return pairingSecret;
            }

            @Override
            public Builder clone() {
                return (Builder) super.clone();
            }

            @Override
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override
            public Builder mergeFrom(Message message) {
                if (message instanceof PairingSecret) {
                    return mergeFrom((PairingSecret) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(PairingSecret pairingSecret) {
                if (pairingSecret == PairingSecret.getDefaultInstance()) {
                    return this;
                }
                if (pairingSecret.getSecret() != ByteString.EMPTY) {
                    setSecret(pairingSecret.getSecret());
                }
                mergeUnknownFields(pairingSecret.unknownFields);
                onChanged();
                return this;
            }

            /* CODE */
            @Override
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                PairingSecret pairingSecret = null;
                try {
                    pairingSecret = (PairingSecret) PairingSecret.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                } catch (InvalidProtocolBufferException e) {
                    pairingSecret = (PairingSecret) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (pairingSecret != null) {
                        mergeFrom(pairingSecret);
                    }
                }
                return this;
            }

            @Override
            public ByteString getSecret() {
                return this.secret_;
            }

            public Builder setSecret(ByteString byteString) {
                Objects.requireNonNull(byteString);
                this.secret_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearSecret() {
                this.secret_ = PairingSecret.getDefaultInstance().getSecret();
                onChanged();
                return this;
            }

            @Override
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }

        public static PairingSecret getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<PairingSecret> parser() {
            return PARSER;
        }

        @Override
        public Parser<PairingSecret> getParserForType() {
            return PARSER;
        }

        @Override
        public PairingSecret getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static final class PairingSecretAck extends GeneratedMessageV3 implements PairingSecretAckOrBuilder {
        private static final PairingSecretAck DEFAULT_INSTANCE = new PairingSecretAck();
        private static final Parser<PairingSecretAck> PARSER = new AbstractParser<PairingSecretAck>() {

            @Override
            public PairingSecretAck parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new PairingSecretAck(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int SECRET_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private ByteString secret_;

        private PairingSecretAck(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
        }

        private PairingSecretAck() {
            this.memoizedIsInitialized = -1;
            this.secret_ = ByteString.EMPTY;
        }

        
        @Override
        public Object newInstance(UnusedPrivateParameter unusedPrivateParameter) {
            return new PairingSecretAck();
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

//        private PairingSecretAck(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
//            this();
//            Objects.requireNonNull(extensionRegistryLite);
//            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
//            boolean z = false;
//            while (!z) {
//                try {
//                    int readTag = codedInputStream.readTag();
//                    if (readTag != 0) {
//                        if (readTag == 10) {
//                            this.secret_ = codedInputStream.readBytes();
//                        } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
//                        }
//                    }
//                    z = true;
//                } catch (InvalidProtocolBufferException e) {
//                    throw e.setUnfinishedMessage(this);
//                } catch (IOException e2) {
//                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
//                } catch (Throwable th) {
//                    this.unknownFields = newBuilder.build();
//                    makeExtensionsImmutable();
//                    throw th;
//                }
//            }
//            this.unknownFields = newBuilder.build();
//            makeExtensionsImmutable();
//        }
        private PairingSecretAck(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                    com.google.protobuf.UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        case 10: {

                            secret_ = input.readBytes();
                            break;
                        }
                        default: {
                            if (!parseUnknownField(
                                    input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                            break;
                        }
                    }
                }
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new com.google.protobuf.InvalidProtocolBufferException(
                        e).setUnfinishedMessage(this);
            } finally {
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }


        public static final Descriptors.Descriptor getDescriptor() {
            return Pairingmessage.internal_static_com_kunal52_pairing_PairingSecretAck_descriptor;
        }

        
        @Override
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Pairingmessage.internal_static_com_kunal52_pairing_PairingSecretAck_fieldAccessorTable.ensureFieldAccessorsInitialized(PairingSecretAck.class, Builder.class);
        }

        @Override
        public ByteString getSecret() {
            return this.secret_;
        }

        @Override
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.secret_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.secret_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.secret_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeBytesSize(1, this.secret_);
            }
            int serializedSize = i2 + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof PairingSecretAck)) {
                return super.equals(obj);
            }
            PairingSecretAck pairingSecretAck = (PairingSecretAck) obj;
            if (getSecret().equals(pairingSecretAck.getSecret()) && this.unknownFields.equals(pairingSecretAck.unknownFields)) {
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getSecret().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static PairingSecretAck parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static PairingSecretAck parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static PairingSecretAck parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static PairingSecretAck parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static PairingSecretAck parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static PairingSecretAck parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static PairingSecretAck parseFrom(InputStream inputStream) throws IOException {
            return (PairingSecretAck) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static PairingSecretAck parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PairingSecretAck) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static PairingSecretAck parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (PairingSecretAck) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static PairingSecretAck parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PairingSecretAck) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static PairingSecretAck parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (PairingSecretAck) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static PairingSecretAck parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PairingSecretAck) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(PairingSecretAck pairingSecretAck) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(pairingSecretAck);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        
        @Override
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements PairingSecretAckOrBuilder {
            private ByteString secret_;

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Pairingmessage.internal_static_com_kunal52_pairing_PairingSecretAck_descriptor;
            }

            
            @Override
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Pairingmessage.internal_static_com_kunal52_pairing_PairingSecretAck_fieldAccessorTable.ensureFieldAccessorsInitialized(PairingSecretAck.class, Builder.class);
            }

            private Builder() {
                this.secret_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.secret_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = PairingSecretAck.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.secret_ = ByteString.EMPTY;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return Pairingmessage.internal_static_com_kunal52_pairing_PairingSecretAck_descriptor;
            }

            @Override
            public PairingSecretAck getDefaultInstanceForType() {
                return PairingSecretAck.getDefaultInstance();
            }

            @Override
            public PairingSecretAck build() {
                PairingSecretAck buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public PairingSecretAck buildPartial() {
                PairingSecretAck pairingSecretAck = new PairingSecretAck(this);
                pairingSecretAck.secret_ = this.secret_;
                onBuilt();
                return pairingSecretAck;
            }

            @Override
            public Builder clone() {
                return (Builder) super.clone();
            }

            @Override
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override
            public Builder mergeFrom(Message message) {
                if (message instanceof PairingSecretAck) {
                    return mergeFrom((PairingSecretAck) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(PairingSecretAck pairingSecretAck) {
                if (pairingSecretAck == PairingSecretAck.getDefaultInstance()) {
                    return this;
                }
                if (pairingSecretAck.getSecret() != ByteString.EMPTY) {
                    setSecret(pairingSecretAck.getSecret());
                }
                mergeUnknownFields(pairingSecretAck.unknownFields);
                onChanged();
                return this;
            }

            /* CODE */
            @Override
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                PairingSecretAck pairingSecretAck = null;
                try {
                    pairingSecretAck = (PairingSecretAck) PairingSecretAck.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                } catch (InvalidProtocolBufferException e) {
                    pairingSecretAck = (PairingSecretAck) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (pairingSecretAck != null) {
                        mergeFrom(pairingSecretAck);
                    }
                }
                return this;
            }

            @Override
            public ByteString getSecret() {
                return this.secret_;
            }

            public Builder setSecret(ByteString byteString) {
                Objects.requireNonNull(byteString);
                this.secret_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearSecret() {
                this.secret_ = PairingSecretAck.getDefaultInstance().getSecret();
                onChanged();
                return this;
            }

            @Override
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }

        public static PairingSecretAck getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<PairingSecretAck> parser() {
            return PARSER;
        }

        @Override
        public Parser<PairingSecretAck> getParserForType() {
            return PARSER;
        }

        @Override
        public PairingSecretAck getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static final class PairingMessage extends GeneratedMessageV3 implements PairingMessageOrBuilder {
        private static final PairingMessage DEFAULT_INSTANCE = new PairingMessage();
        public static final int PAIRING_CONFIGURATION_ACK_FIELD_NUMBER = 31;
        public static final int PAIRING_CONFIGURATION_FIELD_NUMBER = 30;
        public static final int PAIRING_OPTION_FIELD_NUMBER = 20;
        public static final int PAIRING_REQUEST_ACK_FIELD_NUMBER = 11;
        public static final int PAIRING_REQUEST_FIELD_NUMBER = 10;
        public static final int PAIRING_SECRET_ACK_FIELD_NUMBER = 41;
        public static final int PAIRING_SECRET_FIELD_NUMBER = 40;
        private static final Parser<PairingMessage> PARSER = new AbstractParser<PairingMessage>() {

            @Override
            public PairingMessage parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new PairingMessage(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int PROTOCOL_VERSION_FIELD_NUMBER = 1;
        public static final int REQUEST_CASE_FIELD_NUMBER = 3;
        public static final int STATUS_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private PairingConfigurationAck pairingConfigurationAck_;
        private PairingConfiguration pairingConfiguration_;
        private PairingOption pairingOption_;
        private PairingRequestAck pairingRequestAck_;
        private PairingRequest pairingRequest_;
        private PairingSecretAck pairingSecretAck_;
        private PairingSecret pairingSecret_;
        private int protocolVersion_;
        private int requestCase_;
        private int status_;

        private PairingMessage(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
        }

        private PairingMessage() {
            this.memoizedIsInitialized = -1;
            this.status_ = 0;
        }

        
        @Override
        public Object newInstance(UnusedPrivateParameter unusedPrivateParameter) {
            return new PairingMessage();
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

//        private PairingMessage1(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
//            this();
//            Objects.requireNonNull(extensionRegistryLite);
//            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
//            boolean z = false;
//            while (!z) {
//                try {
//                    int readTag = codedInputStream.readTag();
//                    PairingRequest.Builder builder = null;
//                    PairingSecretAck.Builder builder2 = null;
//                    PairingSecret.Builder builder3 = null;
//                    PairingConfigurationAck.Builder builder4 = null;
//                    PairingConfiguration.Builder builder5 = null;
//                    PairingOption.Builder builder6 = null;
//                    PairingRequestAck.Builder builder7 = null;
//                    switch (readTag) {
//                        case 0:
//                            break;
//                        case 8:
//                            this.protocolVersion_ = codedInputStream.readInt32();
//                            continue;
//                        case 16:
//                            this.status_ = codedInputStream.readEnum();
//                            continue;
//                        case 24:
//                            this.requestCase_ = codedInputStream.readInt32();
//                            continue;
//                        case 82:
//                            PairingRequest pairingRequest = this.pairingRequest_;
//                            builder = pairingRequest != null ? pairingRequest.toBuilder() : builder;
//                            PairingRequest pairingRequest2 = (PairingRequest) codedInputStream.readMessage(PairingRequest.parser(), extensionRegistryLite);
//                            this.pairingRequest_ = pairingRequest2;
//                            if (builder != null) {
//                                builder.mergeFrom(pairingRequest2);
//                                this.pairingRequest_ = builder.buildPartial();
//                            } else {
//                                continue;
//                            }
//                        case 90:
//                            PairingRequestAck pairingRequestAck = this.pairingRequestAck_;
//                            builder7 = pairingRequestAck != null ? pairingRequestAck.toBuilder() : builder7;
//                            PairingRequestAck pairingRequestAck2 = (PairingRequestAck) codedInputStream.readMessage(PairingRequestAck.parser(), extensionRegistryLite);
//                            this.pairingRequestAck_ = pairingRequestAck2;
//                            if (builder7 != null) {
//                                builder7.mergeFrom(pairingRequestAck2);
//                                this.pairingRequestAck_ = builder7.buildPartial();
//                            } else {
//                                continue;
//                            }
//                        case Remotemessage.KEYCODE_NUMPAD_LEFT_PAREN_VALUE:
//                            PairingOption pairingOption = this.pairingOption_;
//                            builder6 = pairingOption != null ? pairingOption.toBuilder() : builder6;
//                            PairingOption pairingOption2 = (PairingOption) codedInputStream.readMessage(PairingOption.parser(), extensionRegistryLite);
//                            this.pairingOption_ = pairingOption2;
//                            if (builder6 != null) {
//                                builder6.mergeFrom(pairingOption2);
//                                this.pairingOption_ = builder6.buildPartial();
//                            } else {
//                                continue;
//                            }
//                        case Remotemessage.KEYCODE_TV_ANTENNA_CABLE_VALUE:
//                            PairingConfiguration pairingConfiguration = this.pairingConfiguration_;
//                            builder5 = pairingConfiguration != null ? pairingConfiguration.toBuilder() : builder5;
//                            PairingConfiguration pairingConfiguration2 = (PairingConfiguration) codedInputStream.readMessage(PairingConfiguration.parser(), extensionRegistryLite);
//                            this.pairingConfiguration_ = pairingConfiguration2;
//                            if (builder5 != null) {
//                                builder5.mergeFrom(pairingConfiguration2);
//                                this.pairingConfiguration_ = builder5.buildPartial();
//                            } else {
//                                continue;
//                            }
//                        case 250:
//                            PairingConfigurationAck pairingConfigurationAck = this.pairingConfigurationAck_;
//                            builder4 = pairingConfigurationAck != null ? pairingConfigurationAck.toBuilder() : builder4;
//                            PairingConfigurationAck pairingConfigurationAck2 = (PairingConfigurationAck) codedInputStream.readMessage(PairingConfigurationAck.parser(), extensionRegistryLite);
//                            this.pairingConfigurationAck_ = pairingConfigurationAck2;
//                            if (builder4 != null) {
//                                builder4.mergeFrom(pairingConfigurationAck2);
//                                this.pairingConfigurationAck_ = builder4.buildPartial();
//                            } else {
//                                continue;
//                            }
//                        case MediaError.DetailedErrorCode.DASH_NO_INIT /*{ENCODED_INT: 322}*/:
//                            PairingSecret pairingSecret = this.pairingSecret_;
//                            builder3 = pairingSecret != null ? pairingSecret.toBuilder() : builder3;
//                            PairingSecret pairingSecret2 = (PairingSecret) codedInputStream.readMessage(PairingSecret.parser(), extensionRegistryLite);
//                            this.pairingSecret_ = pairingSecret2;
//                            if (builder3 != null) {
//                                builder3.mergeFrom(pairingSecret2);
//                                this.pairingSecret_ = builder3.buildPartial();
//                            } else {
//                                continue;
//                            }
//                        case 330:
//                            PairingSecretAck pairingSecretAck = this.pairingSecretAck_;
//                            builder2 = pairingSecretAck != null ? pairingSecretAck.toBuilder() : builder2;
//                            PairingSecretAck pairingSecretAck2 = (PairingSecretAck) codedInputStream.readMessage(PairingSecretAck.parser(), extensionRegistryLite);
//                            this.pairingSecretAck_ = pairingSecretAck2;
//                            if (builder2 != null) {
//                                builder2.mergeFrom(pairingSecretAck2);
//                                this.pairingSecretAck_ = builder2.buildPartial();
//                            } else {
//                                continue;
//                            }
//                        default:
//                            if (parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
//                                continue;
//                            }
//                            break;
//                    }
//                    z = true;
//                } catch (InvalidProtocolBufferException e) {
//                    throw e.setUnfinishedMessage(this);
//                } catch (IOException e2) {
//                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
//                } catch (Throwable th) {
//                    this.unknownFields = newBuilder.build();
//                    makeExtensionsImmutable();
//                    throw th;
//                }
//            }
//            this.unknownFields = newBuilder.build();
//            makeExtensionsImmutable();
//        }
        private PairingMessage(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                    com.google.protobuf.UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        case 8: {

                            protocolVersion_ = input.readInt32();
                            break;
                        }
                        case 16: {
                            int rawValue = input.readEnum();

                            status_ = rawValue;
                            break;
                        }
                        case 24: {

                            requestCase_ = input.readInt32();
                            break;
                        }
                        case 82: {
                            PairingRequest.Builder subBuilder = null;
                            if (pairingRequest_ != null) {
                                subBuilder = pairingRequest_.toBuilder();
                            }
                            pairingRequest_ = input.readMessage(PairingRequest.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(pairingRequest_);
                                pairingRequest_ = subBuilder.buildPartial();
                            }

                            break;
                        }
                        case 90: {
                            PairingRequestAck.Builder subBuilder = null;
                            if (pairingRequestAck_ != null) {
                                subBuilder = pairingRequestAck_.toBuilder();
                            }
                            pairingRequestAck_ = input.readMessage(PairingRequestAck.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(pairingRequestAck_);
                                pairingRequestAck_ = subBuilder.buildPartial();
                            }

                            break;
                        }
                        case 162: {
                            PairingOption.Builder subBuilder = null;
                            if (pairingOption_ != null) {
                                subBuilder = pairingOption_.toBuilder();
                            }
                            pairingOption_ = input.readMessage(PairingOption.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(pairingOption_);
                                pairingOption_ = subBuilder.buildPartial();
                            }

                            break;
                        }
                        case 242: {
                            PairingConfiguration.Builder subBuilder = null;
                            if (pairingConfiguration_ != null) {
                                subBuilder = pairingConfiguration_.toBuilder();
                            }
                            pairingConfiguration_ = input.readMessage(PairingConfiguration.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(pairingConfiguration_);
                                pairingConfiguration_ = subBuilder.buildPartial();
                            }

                            break;
                        }
                        case 250: {
                            PairingConfigurationAck.Builder subBuilder = null;
                            if (pairingConfigurationAck_ != null) {
                                subBuilder = pairingConfigurationAck_.toBuilder();
                            }
                            pairingConfigurationAck_ = input.readMessage(PairingConfigurationAck.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(pairingConfigurationAck_);
                                pairingConfigurationAck_ = subBuilder.buildPartial();
                            }

                            break;
                        }
                        case 322: {
                            PairingSecret.Builder subBuilder = null;
                            if (pairingSecret_ != null) {
                                subBuilder = pairingSecret_.toBuilder();
                            }
                            pairingSecret_ = input.readMessage(PairingSecret.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(pairingSecret_);
                                pairingSecret_ = subBuilder.buildPartial();
                            }

                            break;
                        }
                        case 330: {
                            PairingSecretAck.Builder subBuilder = null;
                            if (pairingSecretAck_ != null) {
                                subBuilder = pairingSecretAck_.toBuilder();
                            }
                            pairingSecretAck_ = input.readMessage(PairingSecretAck.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(pairingSecretAck_);
                                pairingSecretAck_ = subBuilder.buildPartial();
                            }

                            break;
                        }
                        default: {
                            if (!parseUnknownField(
                                    input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                            break;
                        }
                    }
                }
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new com.google.protobuf.InvalidProtocolBufferException(
                        e).setUnfinishedMessage(this);
            } finally {
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }


        public static final Descriptors.Descriptor getDescriptor() {
            return Pairingmessage.internal_static_com_kunal52_pairing_PairingMessage_descriptor;
        }

        
        @Override
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Pairingmessage.internal_static_com_kunal52_pairing_PairingMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(PairingMessage.class, Builder.class);
        }

        public enum Status implements ProtocolMessageEnum {
            UNKNOWN(0),
            STATUS_OK(200),
            STATUS_ERROR(400),
            STATUS_BAD_CONFIGURATION(401),
            STATUS_BAD_SECRET(402),
            UNRECOGNIZED(-1);
            
            public static final int STATUS_BAD_CONFIGURATION_VALUE = 401;
            public static final int STATUS_BAD_SECRET_VALUE = 402;
            public static final int STATUS_ERROR_VALUE = 400;
            public static final int STATUS_OK_VALUE = 200;
            public static final int UNKNOWN_VALUE = 0;
            public static final int UNRECOGNIZED_VALUE = -1;
            private static final Status[] VALUES = values();
            private static final Internal.EnumLiteMap<Status> internalValueMap = new Internal.EnumLiteMap<Status>() {

                @Override
                public Status findValueByNumber(int i) {
                    return Status.forNumber(i);
                }
            };
            private final int value;

            @Override
            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.value;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }

            @Deprecated
            public static Status valueOf(int i) {
                return forNumber(i);
            }

            public static Status forNumber(int i) {
                if (i == -1) {
                    return UNRECOGNIZED;
                }
                if (i == 0) {
                    return UNKNOWN;
                }
                if (i == 200) {
                    return STATUS_OK;
                }
                switch (i) {
                    case 400:
                        return STATUS_ERROR;
                    case 401:
                        return STATUS_BAD_CONFIGURATION;
                    case 402:
                        return STATUS_BAD_SECRET;
                    default:
                        return null;
                }
            }

            public static Internal.EnumLiteMap<Status> internalGetValueMap() {
                return internalValueMap;
            }

            @Override
            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                return getDescriptor().getValues().get(ordinal());
            }

            @Override
            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return getDescriptor();
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return PairingMessage.getDescriptor().getEnumTypes().get(0);
            }

            public static Status valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
                if (enumValueDescriptor.getType() != getDescriptor()) {
                    throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                } else if (enumValueDescriptor.getIndex() == -1) {
                    return UNRECOGNIZED;
                } else {
                    return VALUES[enumValueDescriptor.getIndex()];
                }
            }

            private Status(int i) {
                this.value = i;
            }
        }

        @Override
        public int getProtocolVersion() {
            return this.protocolVersion_;
        }

        @Override
        public int getStatusValue() {
            return this.status_;
        }

        @Override
        public Status getStatus() {
            Status valueOf = Status.valueOf(this.status_);
            return valueOf == null ? Status.UNRECOGNIZED : valueOf;
        }

        @Override
        public int getRequestCase() {
            return this.requestCase_;
        }

        @Override
        public boolean hasPairingRequest() {
            return this.pairingRequest_ != null;
        }

        @Override
        public PairingRequest getPairingRequest() {
            PairingRequest pairingRequest = this.pairingRequest_;
            return pairingRequest == null ? PairingRequest.getDefaultInstance() : pairingRequest;
        }

        @Override
        public PairingRequestOrBuilder getPairingRequestOrBuilder() {
            return getPairingRequest();
        }

        @Override
        public boolean hasPairingRequestAck() {
            return this.pairingRequestAck_ != null;
        }

        @Override
        public PairingRequestAck getPairingRequestAck() {
            PairingRequestAck pairingRequestAck = this.pairingRequestAck_;
            return pairingRequestAck == null ? PairingRequestAck.getDefaultInstance() : pairingRequestAck;
        }

        @Override
        public PairingRequestAckOrBuilder getPairingRequestAckOrBuilder() {
            return getPairingRequestAck();
        }

        @Override
        public boolean hasPairingOption() {
            return this.pairingOption_ != null;
        }

        @Override
        public PairingOption getPairingOption() {
            PairingOption pairingOption = this.pairingOption_;
            return pairingOption == null ? PairingOption.getDefaultInstance() : pairingOption;
        }

        @Override
        public PairingOptionOrBuilder getPairingOptionOrBuilder() {
            return getPairingOption();
        }

        @Override
        public boolean hasPairingConfiguration() {
            return this.pairingConfiguration_ != null;
        }

        @Override
        public PairingConfiguration getPairingConfiguration() {
            PairingConfiguration pairingConfiguration = this.pairingConfiguration_;
            return pairingConfiguration == null ? PairingConfiguration.getDefaultInstance() : pairingConfiguration;
        }

        @Override
        public PairingConfigurationOrBuilder getPairingConfigurationOrBuilder() {
            return getPairingConfiguration();
        }

        @Override
        public boolean hasPairingConfigurationAck() {
            return this.pairingConfigurationAck_ != null;
        }

        @Override
        public PairingConfigurationAck getPairingConfigurationAck() {
            PairingConfigurationAck pairingConfigurationAck = this.pairingConfigurationAck_;
            return pairingConfigurationAck == null ? PairingConfigurationAck.getDefaultInstance() : pairingConfigurationAck;
        }

        @Override
        public PairingConfigurationAckOrBuilder getPairingConfigurationAckOrBuilder() {
            return getPairingConfigurationAck();
        }

        @Override
        public boolean hasPairingSecret() {
            return this.pairingSecret_ != null;
        }

        @Override
        public PairingSecret getPairingSecret() {
            PairingSecret pairingSecret = this.pairingSecret_;
            return pairingSecret == null ? PairingSecret.getDefaultInstance() : pairingSecret;
        }

        @Override
        public PairingSecretOrBuilder getPairingSecretOrBuilder() {
            return getPairingSecret();
        }

        @Override
        public boolean hasPairingSecretAck() {
            return this.pairingSecretAck_ != null;
        }

        @Override
        public PairingSecretAck getPairingSecretAck() {
            PairingSecretAck pairingSecretAck = this.pairingSecretAck_;
            return pairingSecretAck == null ? PairingSecretAck.getDefaultInstance() : pairingSecretAck;
        }

        @Override
        public PairingSecretAckOrBuilder getPairingSecretAckOrBuilder() {
            return getPairingSecretAck();
        }

        @Override
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.protocolVersion_;
            if (i != 0) {
                codedOutputStream.writeInt32(1, i);
            }
            if (this.status_ != Status.UNKNOWN.getNumber()) {
                codedOutputStream.writeEnum(2, this.status_);
            }
            int i2 = this.requestCase_;
            if (i2 != 0) {
                codedOutputStream.writeInt32(3, i2);
            }
            if (this.pairingRequest_ != null) {
                codedOutputStream.writeMessage(10, getPairingRequest());
            }
            if (this.pairingRequestAck_ != null) {
                codedOutputStream.writeMessage(11, getPairingRequestAck());
            }
            if (this.pairingOption_ != null) {
                codedOutputStream.writeMessage(20, getPairingOption());
            }
            if (this.pairingConfiguration_ != null) {
                codedOutputStream.writeMessage(30, getPairingConfiguration());
            }
            if (this.pairingConfigurationAck_ != null) {
                codedOutputStream.writeMessage(31, getPairingConfigurationAck());
            }
            if (this.pairingSecret_ != null) {
                codedOutputStream.writeMessage(40, getPairingSecret());
            }
            if (this.pairingSecretAck_ != null) {
                codedOutputStream.writeMessage(41, getPairingSecretAck());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            int i3 = this.protocolVersion_;
            if (i3 != 0) {
                i2 = 0 + CodedOutputStream.computeInt32Size(1, i3);
            }
            if (this.status_ != Status.UNKNOWN.getNumber()) {
                i2 += CodedOutputStream.computeEnumSize(2, this.status_);
            }
            int i4 = this.requestCase_;
            if (i4 != 0) {
                i2 += CodedOutputStream.computeInt32Size(3, i4);
            }
            if (this.pairingRequest_ != null) {
                i2 += CodedOutputStream.computeMessageSize(10, getPairingRequest());
            }
            if (this.pairingRequestAck_ != null) {
                i2 += CodedOutputStream.computeMessageSize(11, getPairingRequestAck());
            }
            if (this.pairingOption_ != null) {
                i2 += CodedOutputStream.computeMessageSize(20, getPairingOption());
            }
            if (this.pairingConfiguration_ != null) {
                i2 += CodedOutputStream.computeMessageSize(30, getPairingConfiguration());
            }
            if (this.pairingConfigurationAck_ != null) {
                i2 += CodedOutputStream.computeMessageSize(31, getPairingConfigurationAck());
            }
            if (this.pairingSecret_ != null) {
                i2 += CodedOutputStream.computeMessageSize(40, getPairingSecret());
            }
            if (this.pairingSecretAck_ != null) {
                i2 += CodedOutputStream.computeMessageSize(41, getPairingSecretAck());
            }
            int serializedSize = i2 + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof PairingMessage)) {
                return super.equals(obj);
            }
            PairingMessage pairingMessage = (PairingMessage) obj;
            if (getProtocolVersion() != pairingMessage.getProtocolVersion() || this.status_ != pairingMessage.status_ || getRequestCase() != pairingMessage.getRequestCase() || hasPairingRequest() != pairingMessage.hasPairingRequest()) {
                return false;
            }
            if ((hasPairingRequest() && !getPairingRequest().equals(pairingMessage.getPairingRequest())) || hasPairingRequestAck() != pairingMessage.hasPairingRequestAck()) {
                return false;
            }
            if ((hasPairingRequestAck() && !getPairingRequestAck().equals(pairingMessage.getPairingRequestAck())) || hasPairingOption() != pairingMessage.hasPairingOption()) {
                return false;
            }
            if ((hasPairingOption() && !getPairingOption().equals(pairingMessage.getPairingOption())) || hasPairingConfiguration() != pairingMessage.hasPairingConfiguration()) {
                return false;
            }
            if ((hasPairingConfiguration() && !getPairingConfiguration().equals(pairingMessage.getPairingConfiguration())) || hasPairingConfigurationAck() != pairingMessage.hasPairingConfigurationAck()) {
                return false;
            }
            if ((hasPairingConfigurationAck() && !getPairingConfigurationAck().equals(pairingMessage.getPairingConfigurationAck())) || hasPairingSecret() != pairingMessage.hasPairingSecret()) {
                return false;
            }
            if ((hasPairingSecret() && !getPairingSecret().equals(pairingMessage.getPairingSecret())) || hasPairingSecretAck() != pairingMessage.hasPairingSecretAck()) {
                return false;
            }
            if ((!hasPairingSecretAck() || getPairingSecretAck().equals(pairingMessage.getPairingSecretAck())) && this.unknownFields.equals(pairingMessage.unknownFields)) {
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getProtocolVersion()) * 37) + 2) * 53) + this.status_) * 37) + 3) * 53) + getRequestCase();
            if (hasPairingRequest()) {
                hashCode = (((hashCode * 37) + 10) * 53) + getPairingRequest().hashCode();
            }
            if (hasPairingRequestAck()) {
                hashCode = (((hashCode * 37) + 11) * 53) + getPairingRequestAck().hashCode();
            }
            if (hasPairingOption()) {
                hashCode = (((hashCode * 37) + 20) * 53) + getPairingOption().hashCode();
            }
            if (hasPairingConfiguration()) {
                hashCode = (((hashCode * 37) + 30) * 53) + getPairingConfiguration().hashCode();
            }
            if (hasPairingConfigurationAck()) {
                hashCode = (((hashCode * 37) + 31) * 53) + getPairingConfigurationAck().hashCode();
            }
            if (hasPairingSecret()) {
                hashCode = (((hashCode * 37) + 40) * 53) + getPairingSecret().hashCode();
            }
            if (hasPairingSecretAck()) {
                hashCode = (((hashCode * 37) + 41) * 53) + getPairingSecretAck().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static PairingMessage parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static PairingMessage parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static PairingMessage parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static PairingMessage parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static PairingMessage parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static PairingMessage parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static PairingMessage parseFrom(InputStream inputStream) throws IOException {
            return (PairingMessage) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static PairingMessage parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PairingMessage) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static PairingMessage parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (PairingMessage) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static PairingMessage parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PairingMessage) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static PairingMessage parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (PairingMessage) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static PairingMessage parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PairingMessage) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(PairingMessage pairingMessage) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(pairingMessage);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        
        @Override
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements PairingMessageOrBuilder {
            private SingleFieldBuilderV3<PairingConfigurationAck, PairingConfigurationAck.Builder, PairingConfigurationAckOrBuilder> pairingConfigurationAckBuilder_;
            private PairingConfigurationAck pairingConfigurationAck_;
            private SingleFieldBuilderV3<PairingConfiguration, PairingConfiguration.Builder, PairingConfigurationOrBuilder> pairingConfigurationBuilder_;
            private PairingConfiguration pairingConfiguration_;
            private SingleFieldBuilderV3<PairingOption, PairingOption.Builder, PairingOptionOrBuilder> pairingOptionBuilder_;
            private PairingOption pairingOption_;
            private SingleFieldBuilderV3<PairingRequestAck, PairingRequestAck.Builder, PairingRequestAckOrBuilder> pairingRequestAckBuilder_;
            private PairingRequestAck pairingRequestAck_;
            private SingleFieldBuilderV3<PairingRequest, PairingRequest.Builder, PairingRequestOrBuilder> pairingRequestBuilder_;
            private PairingRequest pairingRequest_;
            private SingleFieldBuilderV3<PairingSecretAck, PairingSecretAck.Builder, PairingSecretAckOrBuilder> pairingSecretAckBuilder_;
            private PairingSecretAck pairingSecretAck_;
            private SingleFieldBuilderV3<PairingSecret, PairingSecret.Builder, PairingSecretOrBuilder> pairingSecretBuilder_;
            private PairingSecret pairingSecret_;
            private int protocolVersion_;
            private int requestCase_;
            private int status_;

            @Override
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Pairingmessage.internal_static_com_kunal52_pairing_PairingMessage_descriptor;
            }

            
            @Override
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Pairingmessage.internal_static_com_kunal52_pairing_PairingMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(PairingMessage.class, Builder.class);
            }

            private Builder() {
                this.status_ = 0;
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.status_ = 0;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = PairingMessage.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.protocolVersion_ = 0;
                this.status_ = 0;
                this.requestCase_ = 0;
                if (this.pairingRequestBuilder_ == null) {
                    this.pairingRequest_ = null;
                } else {
                    this.pairingRequest_ = null;
                    this.pairingRequestBuilder_ = null;
                }
                if (this.pairingRequestAckBuilder_ == null) {
                    this.pairingRequestAck_ = null;
                } else {
                    this.pairingRequestAck_ = null;
                    this.pairingRequestAckBuilder_ = null;
                }
                if (this.pairingOptionBuilder_ == null) {
                    this.pairingOption_ = null;
                } else {
                    this.pairingOption_ = null;
                    this.pairingOptionBuilder_ = null;
                }
                if (this.pairingConfigurationBuilder_ == null) {
                    this.pairingConfiguration_ = null;
                } else {
                    this.pairingConfiguration_ = null;
                    this.pairingConfigurationBuilder_ = null;
                }
                if (this.pairingConfigurationAckBuilder_ == null) {
                    this.pairingConfigurationAck_ = null;
                } else {
                    this.pairingConfigurationAck_ = null;
                    this.pairingConfigurationAckBuilder_ = null;
                }
                if (this.pairingSecretBuilder_ == null) {
                    this.pairingSecret_ = null;
                } else {
                    this.pairingSecret_ = null;
                    this.pairingSecretBuilder_ = null;
                }
                if (this.pairingSecretAckBuilder_ == null) {
                    this.pairingSecretAck_ = null;
                } else {
                    this.pairingSecretAck_ = null;
                    this.pairingSecretAckBuilder_ = null;
                }
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return Pairingmessage.internal_static_com_kunal52_pairing_PairingMessage_descriptor;
            }

            @Override
            public PairingMessage getDefaultInstanceForType() {
                return PairingMessage.getDefaultInstance();
            }

            @Override
            public PairingMessage build() {
                PairingMessage buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public PairingMessage buildPartial() {
                PairingMessage pairingMessage = new PairingMessage(this);
                pairingMessage.protocolVersion_ = this.protocolVersion_;
                pairingMessage.status_ = this.status_;
                pairingMessage.requestCase_ = this.requestCase_;
                SingleFieldBuilderV3<PairingRequest, PairingRequest.Builder, PairingRequestOrBuilder> singleFieldBuilderV3 = this.pairingRequestBuilder_;
                if (singleFieldBuilderV3 == null) {
                    pairingMessage.pairingRequest_ = this.pairingRequest_;
                } else {
                    pairingMessage.pairingRequest_ = singleFieldBuilderV3.build();
                }
                SingleFieldBuilderV3<PairingRequestAck, PairingRequestAck.Builder, PairingRequestAckOrBuilder> singleFieldBuilderV32 = this.pairingRequestAckBuilder_;
                if (singleFieldBuilderV32 == null) {
                    pairingMessage.pairingRequestAck_ = this.pairingRequestAck_;
                } else {
                    pairingMessage.pairingRequestAck_ = singleFieldBuilderV32.build();
                }
                SingleFieldBuilderV3<PairingOption, PairingOption.Builder, PairingOptionOrBuilder> singleFieldBuilderV33 = this.pairingOptionBuilder_;
                if (singleFieldBuilderV33 == null) {
                    pairingMessage.pairingOption_ = this.pairingOption_;
                } else {
                    pairingMessage.pairingOption_ = singleFieldBuilderV33.build();
                }
                SingleFieldBuilderV3<PairingConfiguration, PairingConfiguration.Builder, PairingConfigurationOrBuilder> singleFieldBuilderV34 = this.pairingConfigurationBuilder_;
                if (singleFieldBuilderV34 == null) {
                    pairingMessage.pairingConfiguration_ = this.pairingConfiguration_;
                } else {
                    pairingMessage.pairingConfiguration_ = singleFieldBuilderV34.build();
                }
                SingleFieldBuilderV3<PairingConfigurationAck, PairingConfigurationAck.Builder, PairingConfigurationAckOrBuilder> singleFieldBuilderV35 = this.pairingConfigurationAckBuilder_;
                if (singleFieldBuilderV35 == null) {
                    pairingMessage.pairingConfigurationAck_ = this.pairingConfigurationAck_;
                } else {
                    pairingMessage.pairingConfigurationAck_ = singleFieldBuilderV35.build();
                }
                SingleFieldBuilderV3<PairingSecret, PairingSecret.Builder, PairingSecretOrBuilder> singleFieldBuilderV36 = this.pairingSecretBuilder_;
                if (singleFieldBuilderV36 == null) {
                    pairingMessage.pairingSecret_ = this.pairingSecret_;
                } else {
                    pairingMessage.pairingSecret_ = singleFieldBuilderV36.build();
                }
                SingleFieldBuilderV3<PairingSecretAck, PairingSecretAck.Builder, PairingSecretAckOrBuilder> singleFieldBuilderV37 = this.pairingSecretAckBuilder_;
                if (singleFieldBuilderV37 == null) {
                    pairingMessage.pairingSecretAck_ = this.pairingSecretAck_;
                } else {
                    pairingMessage.pairingSecretAck_ = singleFieldBuilderV37.build();
                }
                onBuilt();
                return pairingMessage;
            }

            @Override
            public Builder clone() {
                return (Builder) super.clone();
            }

            @Override
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override
            public Builder mergeFrom(Message message) {
                if (message instanceof PairingMessage) {
                    return mergeFrom((PairingMessage) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(PairingMessage pairingMessage) {
                if (pairingMessage == PairingMessage.getDefaultInstance()) {
                    return this;
                }
                if (pairingMessage.getProtocolVersion() != 0) {
                    setProtocolVersion(pairingMessage.getProtocolVersion());
                }
                if (pairingMessage.status_ != 0) {
                    setStatusValue(pairingMessage.getStatusValue());
                }
                if (pairingMessage.getRequestCase() != 0) {
                    setRequestCase(pairingMessage.getRequestCase());
                }
                if (pairingMessage.hasPairingRequest()) {
                    mergePairingRequest(pairingMessage.getPairingRequest());
                }
                if (pairingMessage.hasPairingRequestAck()) {
                    mergePairingRequestAck(pairingMessage.getPairingRequestAck());
                }
                if (pairingMessage.hasPairingOption()) {
                    mergePairingOption(pairingMessage.getPairingOption());
                }
                if (pairingMessage.hasPairingConfiguration()) {
                    mergePairingConfiguration(pairingMessage.getPairingConfiguration());
                }
                if (pairingMessage.hasPairingConfigurationAck()) {
                    mergePairingConfigurationAck(pairingMessage.getPairingConfigurationAck());
                }
                if (pairingMessage.hasPairingSecret()) {
                    mergePairingSecret(pairingMessage.getPairingSecret());
                }
                if (pairingMessage.hasPairingSecretAck()) {
                    mergePairingSecretAck(pairingMessage.getPairingSecretAck());
                }
                mergeUnknownFields(pairingMessage.unknownFields);
                onChanged();
                return this;
            }

            /* CODE */
            @Override
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                PairingMessage pairingMessage = null;
                try {
                    pairingMessage = (PairingMessage) PairingMessage.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                } catch (InvalidProtocolBufferException e) {
                    pairingMessage = (PairingMessage) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (pairingMessage != null) {
                        mergeFrom(pairingMessage);
                    }
                }
                return this;
            }

            @Override
            public int getProtocolVersion() {
                return this.protocolVersion_;
            }

            public Builder setProtocolVersion(int i) {
                this.protocolVersion_ = i;
                onChanged();
                return this;
            }

            public Builder clearProtocolVersion() {
                this.protocolVersion_ = 0;
                onChanged();
                return this;
            }

            @Override
            public int getStatusValue() {
                return this.status_;
            }

            public Builder setStatusValue(int i) {
                this.status_ = i;
                onChanged();
                return this;
            }

            @Override
            public Status getStatus() {
                Status valueOf = Status.valueOf(this.status_);
                return valueOf == null ? Status.UNRECOGNIZED : valueOf;
            }

            public Builder setStatus(Status status) {
                Objects.requireNonNull(status);
                this.status_ = status.getNumber();
                onChanged();
                return this;
            }

            public Builder clearStatus() {
                this.status_ = 0;
                onChanged();
                return this;
            }

            @Override
            public int getRequestCase() {
                return this.requestCase_;
            }

            public Builder setRequestCase(int i) {
                this.requestCase_ = i;
                onChanged();
                return this;
            }

            public Builder clearRequestCase() {
                this.requestCase_ = 0;
                onChanged();
                return this;
            }

            @Override
            public boolean hasPairingRequest() {
                return (this.pairingRequestBuilder_ == null && this.pairingRequest_ == null) ? false : true;
            }

            @Override
            public PairingRequest getPairingRequest() {
                SingleFieldBuilderV3<PairingRequest, PairingRequest.Builder, PairingRequestOrBuilder> singleFieldBuilderV3 = this.pairingRequestBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                PairingRequest pairingRequest = this.pairingRequest_;
                return pairingRequest == null ? PairingRequest.getDefaultInstance() : pairingRequest;
            }

            public Builder setPairingRequest(PairingRequest pairingRequest) {
                SingleFieldBuilderV3<PairingRequest, PairingRequest.Builder, PairingRequestOrBuilder> singleFieldBuilderV3 = this.pairingRequestBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Objects.requireNonNull(pairingRequest);
                    this.pairingRequest_ = pairingRequest;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(pairingRequest);
                }
                return this;
            }

            public Builder setPairingRequest(PairingRequest.Builder builder) {
                SingleFieldBuilderV3<PairingRequest, PairingRequest.Builder, PairingRequestOrBuilder> singleFieldBuilderV3 = this.pairingRequestBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.pairingRequest_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergePairingRequest(PairingRequest pairingRequest) {
                SingleFieldBuilderV3<PairingRequest, PairingRequest.Builder, PairingRequestOrBuilder> singleFieldBuilderV3 = this.pairingRequestBuilder_;
                if (singleFieldBuilderV3 == null) {
                    PairingRequest pairingRequest2 = this.pairingRequest_;
                    if (pairingRequest2 != null) {
                        this.pairingRequest_ = PairingRequest.newBuilder(pairingRequest2).mergeFrom(pairingRequest).buildPartial();
                    } else {
                        this.pairingRequest_ = pairingRequest;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(pairingRequest);
                }
                return this;
            }

            public Builder clearPairingRequest() {
                if (this.pairingRequestBuilder_ == null) {
                    this.pairingRequest_ = null;
                    onChanged();
                } else {
                    this.pairingRequest_ = null;
                    this.pairingRequestBuilder_ = null;
                }
                return this;
            }

            public PairingRequest.Builder getPairingRequestBuilder() {
                onChanged();
                return getPairingRequestFieldBuilder().getBuilder();
            }

            @Override
            public PairingRequestOrBuilder getPairingRequestOrBuilder() {
                SingleFieldBuilderV3<PairingRequest, PairingRequest.Builder, PairingRequestOrBuilder> singleFieldBuilderV3 = this.pairingRequestBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                PairingRequest pairingRequest = this.pairingRequest_;
                return pairingRequest == null ? PairingRequest.getDefaultInstance() : pairingRequest;
            }

            private SingleFieldBuilderV3<PairingRequest, PairingRequest.Builder, PairingRequestOrBuilder> getPairingRequestFieldBuilder() {
                if (this.pairingRequestBuilder_ == null) {
                    this.pairingRequestBuilder_ = new SingleFieldBuilderV3<>(getPairingRequest(), getParentForChildren(), isClean());
                    this.pairingRequest_ = null;
                }
                return this.pairingRequestBuilder_;
            }

            @Override
            public boolean hasPairingRequestAck() {
                return (this.pairingRequestAckBuilder_ == null && this.pairingRequestAck_ == null) ? false : true;
            }

            @Override
            public PairingRequestAck getPairingRequestAck() {
                SingleFieldBuilderV3<PairingRequestAck, PairingRequestAck.Builder, PairingRequestAckOrBuilder> singleFieldBuilderV3 = this.pairingRequestAckBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                PairingRequestAck pairingRequestAck = this.pairingRequestAck_;
                return pairingRequestAck == null ? PairingRequestAck.getDefaultInstance() : pairingRequestAck;
            }

            public Builder setPairingRequestAck(PairingRequestAck pairingRequestAck) {
                SingleFieldBuilderV3<PairingRequestAck, PairingRequestAck.Builder, PairingRequestAckOrBuilder> singleFieldBuilderV3 = this.pairingRequestAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Objects.requireNonNull(pairingRequestAck);
                    this.pairingRequestAck_ = pairingRequestAck;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(pairingRequestAck);
                }
                return this;
            }

            public Builder setPairingRequestAck(PairingRequestAck.Builder builder) {
                SingleFieldBuilderV3<PairingRequestAck, PairingRequestAck.Builder, PairingRequestAckOrBuilder> singleFieldBuilderV3 = this.pairingRequestAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.pairingRequestAck_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergePairingRequestAck(PairingRequestAck pairingRequestAck) {
                SingleFieldBuilderV3<PairingRequestAck, PairingRequestAck.Builder, PairingRequestAckOrBuilder> singleFieldBuilderV3 = this.pairingRequestAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    PairingRequestAck pairingRequestAck2 = this.pairingRequestAck_;
                    if (pairingRequestAck2 != null) {
                        this.pairingRequestAck_ = PairingRequestAck.newBuilder(pairingRequestAck2).mergeFrom(pairingRequestAck).buildPartial();
                    } else {
                        this.pairingRequestAck_ = pairingRequestAck;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(pairingRequestAck);
                }
                return this;
            }

            public Builder clearPairingRequestAck() {
                if (this.pairingRequestAckBuilder_ == null) {
                    this.pairingRequestAck_ = null;
                    onChanged();
                } else {
                    this.pairingRequestAck_ = null;
                    this.pairingRequestAckBuilder_ = null;
                }
                return this;
            }

            public PairingRequestAck.Builder getPairingRequestAckBuilder() {
                onChanged();
                return getPairingRequestAckFieldBuilder().getBuilder();
            }

            @Override
            public PairingRequestAckOrBuilder getPairingRequestAckOrBuilder() {
                SingleFieldBuilderV3<PairingRequestAck, PairingRequestAck.Builder, PairingRequestAckOrBuilder> singleFieldBuilderV3 = this.pairingRequestAckBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                PairingRequestAck pairingRequestAck = this.pairingRequestAck_;
                return pairingRequestAck == null ? PairingRequestAck.getDefaultInstance() : pairingRequestAck;
            }

            private SingleFieldBuilderV3<PairingRequestAck, PairingRequestAck.Builder, PairingRequestAckOrBuilder> getPairingRequestAckFieldBuilder() {
                if (this.pairingRequestAckBuilder_ == null) {
                    this.pairingRequestAckBuilder_ = new SingleFieldBuilderV3<>(getPairingRequestAck(), getParentForChildren(), isClean());
                    this.pairingRequestAck_ = null;
                }
                return this.pairingRequestAckBuilder_;
            }

            @Override
            public boolean hasPairingOption() {
                return (this.pairingOptionBuilder_ == null && this.pairingOption_ == null) ? false : true;
            }

            @Override
            public PairingOption getPairingOption() {
                SingleFieldBuilderV3<PairingOption, PairingOption.Builder, PairingOptionOrBuilder> singleFieldBuilderV3 = this.pairingOptionBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                PairingOption pairingOption = this.pairingOption_;
                return pairingOption == null ? PairingOption.getDefaultInstance() : pairingOption;
            }

            public Builder setPairingOption(PairingOption pairingOption) {
                SingleFieldBuilderV3<PairingOption, PairingOption.Builder, PairingOptionOrBuilder> singleFieldBuilderV3 = this.pairingOptionBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Objects.requireNonNull(pairingOption);
                    this.pairingOption_ = pairingOption;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(pairingOption);
                }
                return this;
            }

            public Builder setPairingOption(PairingOption.Builder builder) {
                SingleFieldBuilderV3<PairingOption, PairingOption.Builder, PairingOptionOrBuilder> singleFieldBuilderV3 = this.pairingOptionBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.pairingOption_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergePairingOption(PairingOption pairingOption) {
                SingleFieldBuilderV3<PairingOption, PairingOption.Builder, PairingOptionOrBuilder> singleFieldBuilderV3 = this.pairingOptionBuilder_;
                if (singleFieldBuilderV3 == null) {
                    PairingOption pairingOption2 = this.pairingOption_;
                    if (pairingOption2 != null) {
                        this.pairingOption_ = PairingOption.newBuilder(pairingOption2).mergeFrom(pairingOption).buildPartial();
                    } else {
                        this.pairingOption_ = pairingOption;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(pairingOption);
                }
                return this;
            }

            public Builder clearPairingOption() {
                if (this.pairingOptionBuilder_ == null) {
                    this.pairingOption_ = null;
                    onChanged();
                } else {
                    this.pairingOption_ = null;
                    this.pairingOptionBuilder_ = null;
                }
                return this;
            }

            public PairingOption.Builder getPairingOptionBuilder() {
                onChanged();
                return getPairingOptionFieldBuilder().getBuilder();
            }

            @Override
            public PairingOptionOrBuilder getPairingOptionOrBuilder() {
                SingleFieldBuilderV3<PairingOption, PairingOption.Builder, PairingOptionOrBuilder> singleFieldBuilderV3 = this.pairingOptionBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                PairingOption pairingOption = this.pairingOption_;
                return pairingOption == null ? PairingOption.getDefaultInstance() : pairingOption;
            }

            private SingleFieldBuilderV3<PairingOption, PairingOption.Builder, PairingOptionOrBuilder> getPairingOptionFieldBuilder() {
                if (this.pairingOptionBuilder_ == null) {
                    this.pairingOptionBuilder_ = new SingleFieldBuilderV3<>(getPairingOption(), getParentForChildren(), isClean());
                    this.pairingOption_ = null;
                }
                return this.pairingOptionBuilder_;
            }

            @Override
            public boolean hasPairingConfiguration() {
                return (this.pairingConfigurationBuilder_ == null && this.pairingConfiguration_ == null) ? false : true;
            }

            @Override
            public PairingConfiguration getPairingConfiguration() {
                SingleFieldBuilderV3<PairingConfiguration, PairingConfiguration.Builder, PairingConfigurationOrBuilder> singleFieldBuilderV3 = this.pairingConfigurationBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                PairingConfiguration pairingConfiguration = this.pairingConfiguration_;
                return pairingConfiguration == null ? PairingConfiguration.getDefaultInstance() : pairingConfiguration;
            }

            public Builder setPairingConfiguration(PairingConfiguration pairingConfiguration) {
                SingleFieldBuilderV3<PairingConfiguration, PairingConfiguration.Builder, PairingConfigurationOrBuilder> singleFieldBuilderV3 = this.pairingConfigurationBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Objects.requireNonNull(pairingConfiguration);
                    this.pairingConfiguration_ = pairingConfiguration;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(pairingConfiguration);
                }
                return this;
            }

            public Builder setPairingConfiguration(PairingConfiguration.Builder builder) {
                SingleFieldBuilderV3<PairingConfiguration, PairingConfiguration.Builder, PairingConfigurationOrBuilder> singleFieldBuilderV3 = this.pairingConfigurationBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.pairingConfiguration_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergePairingConfiguration(PairingConfiguration pairingConfiguration) {
                SingleFieldBuilderV3<PairingConfiguration, PairingConfiguration.Builder, PairingConfigurationOrBuilder> singleFieldBuilderV3 = this.pairingConfigurationBuilder_;
                if (singleFieldBuilderV3 == null) {
                    PairingConfiguration pairingConfiguration2 = this.pairingConfiguration_;
                    if (pairingConfiguration2 != null) {
                        this.pairingConfiguration_ = PairingConfiguration.newBuilder(pairingConfiguration2).mergeFrom(pairingConfiguration).buildPartial();
                    } else {
                        this.pairingConfiguration_ = pairingConfiguration;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(pairingConfiguration);
                }
                return this;
            }

            public Builder clearPairingConfiguration() {
                if (this.pairingConfigurationBuilder_ == null) {
                    this.pairingConfiguration_ = null;
                    onChanged();
                } else {
                    this.pairingConfiguration_ = null;
                    this.pairingConfigurationBuilder_ = null;
                }
                return this;
            }

            public PairingConfiguration.Builder getPairingConfigurationBuilder() {
                onChanged();
                return getPairingConfigurationFieldBuilder().getBuilder();
            }

            @Override
            public PairingConfigurationOrBuilder getPairingConfigurationOrBuilder() {
                SingleFieldBuilderV3<PairingConfiguration, PairingConfiguration.Builder, PairingConfigurationOrBuilder> singleFieldBuilderV3 = this.pairingConfigurationBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                PairingConfiguration pairingConfiguration = this.pairingConfiguration_;
                return pairingConfiguration == null ? PairingConfiguration.getDefaultInstance() : pairingConfiguration;
            }

            private SingleFieldBuilderV3<PairingConfiguration, PairingConfiguration.Builder, PairingConfigurationOrBuilder> getPairingConfigurationFieldBuilder() {
                if (this.pairingConfigurationBuilder_ == null) {
                    this.pairingConfigurationBuilder_ = new SingleFieldBuilderV3<>(getPairingConfiguration(), getParentForChildren(), isClean());
                    this.pairingConfiguration_ = null;
                }
                return this.pairingConfigurationBuilder_;
            }

            @Override
            public boolean hasPairingConfigurationAck() {
                return (this.pairingConfigurationAckBuilder_ == null && this.pairingConfigurationAck_ == null) ? false : true;
            }

            @Override
            public PairingConfigurationAck getPairingConfigurationAck() {
                SingleFieldBuilderV3<PairingConfigurationAck, PairingConfigurationAck.Builder, PairingConfigurationAckOrBuilder> singleFieldBuilderV3 = this.pairingConfigurationAckBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                PairingConfigurationAck pairingConfigurationAck = this.pairingConfigurationAck_;
                return pairingConfigurationAck == null ? PairingConfigurationAck.getDefaultInstance() : pairingConfigurationAck;
            }

            public Builder setPairingConfigurationAck(PairingConfigurationAck pairingConfigurationAck) {
                SingleFieldBuilderV3<PairingConfigurationAck, PairingConfigurationAck.Builder, PairingConfigurationAckOrBuilder> singleFieldBuilderV3 = this.pairingConfigurationAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Objects.requireNonNull(pairingConfigurationAck);
                    this.pairingConfigurationAck_ = pairingConfigurationAck;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(pairingConfigurationAck);
                }
                return this;
            }

            public Builder setPairingConfigurationAck(PairingConfigurationAck.Builder builder) {
                SingleFieldBuilderV3<PairingConfigurationAck, PairingConfigurationAck.Builder, PairingConfigurationAckOrBuilder> singleFieldBuilderV3 = this.pairingConfigurationAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.pairingConfigurationAck_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergePairingConfigurationAck(PairingConfigurationAck pairingConfigurationAck) {
                SingleFieldBuilderV3<PairingConfigurationAck, PairingConfigurationAck.Builder, PairingConfigurationAckOrBuilder> singleFieldBuilderV3 = this.pairingConfigurationAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    PairingConfigurationAck pairingConfigurationAck2 = this.pairingConfigurationAck_;
                    if (pairingConfigurationAck2 != null) {
                        this.pairingConfigurationAck_ = PairingConfigurationAck.newBuilder(pairingConfigurationAck2).mergeFrom(pairingConfigurationAck).buildPartial();
                    } else {
                        this.pairingConfigurationAck_ = pairingConfigurationAck;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(pairingConfigurationAck);
                }
                return this;
            }

            public Builder clearPairingConfigurationAck() {
                if (this.pairingConfigurationAckBuilder_ == null) {
                    this.pairingConfigurationAck_ = null;
                    onChanged();
                } else {
                    this.pairingConfigurationAck_ = null;
                    this.pairingConfigurationAckBuilder_ = null;
                }
                return this;
            }

            public PairingConfigurationAck.Builder getPairingConfigurationAckBuilder() {
                onChanged();
                return getPairingConfigurationAckFieldBuilder().getBuilder();
            }

            @Override
            public PairingConfigurationAckOrBuilder getPairingConfigurationAckOrBuilder() {
                SingleFieldBuilderV3<PairingConfigurationAck, PairingConfigurationAck.Builder, PairingConfigurationAckOrBuilder> singleFieldBuilderV3 = this.pairingConfigurationAckBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                PairingConfigurationAck pairingConfigurationAck = this.pairingConfigurationAck_;
                return pairingConfigurationAck == null ? PairingConfigurationAck.getDefaultInstance() : pairingConfigurationAck;
            }

            private SingleFieldBuilderV3<PairingConfigurationAck, PairingConfigurationAck.Builder, PairingConfigurationAckOrBuilder> getPairingConfigurationAckFieldBuilder() {
                if (this.pairingConfigurationAckBuilder_ == null) {
                    this.pairingConfigurationAckBuilder_ = new SingleFieldBuilderV3<>(getPairingConfigurationAck(), getParentForChildren(), isClean());
                    this.pairingConfigurationAck_ = null;
                }
                return this.pairingConfigurationAckBuilder_;
            }

            @Override
            public boolean hasPairingSecret() {
                return (this.pairingSecretBuilder_ == null && this.pairingSecret_ == null) ? false : true;
            }

            @Override
            public PairingSecret getPairingSecret() {
                SingleFieldBuilderV3<PairingSecret, PairingSecret.Builder, PairingSecretOrBuilder> singleFieldBuilderV3 = this.pairingSecretBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                PairingSecret pairingSecret = this.pairingSecret_;
                return pairingSecret == null ? PairingSecret.getDefaultInstance() : pairingSecret;
            }

            public Builder setPairingSecret(PairingSecret pairingSecret) {
                SingleFieldBuilderV3<PairingSecret, PairingSecret.Builder, PairingSecretOrBuilder> singleFieldBuilderV3 = this.pairingSecretBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Objects.requireNonNull(pairingSecret);
                    this.pairingSecret_ = pairingSecret;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(pairingSecret);
                }
                return this;
            }

            public Builder setPairingSecret(PairingSecret.Builder builder) {
                SingleFieldBuilderV3<PairingSecret, PairingSecret.Builder, PairingSecretOrBuilder> singleFieldBuilderV3 = this.pairingSecretBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.pairingSecret_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergePairingSecret(PairingSecret pairingSecret) {
                SingleFieldBuilderV3<PairingSecret, PairingSecret.Builder, PairingSecretOrBuilder> singleFieldBuilderV3 = this.pairingSecretBuilder_;
                if (singleFieldBuilderV3 == null) {
                    PairingSecret pairingSecret2 = this.pairingSecret_;
                    if (pairingSecret2 != null) {
                        this.pairingSecret_ = PairingSecret.newBuilder(pairingSecret2).mergeFrom(pairingSecret).buildPartial();
                    } else {
                        this.pairingSecret_ = pairingSecret;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(pairingSecret);
                }
                return this;
            }

            public Builder clearPairingSecret() {
                if (this.pairingSecretBuilder_ == null) {
                    this.pairingSecret_ = null;
                    onChanged();
                } else {
                    this.pairingSecret_ = null;
                    this.pairingSecretBuilder_ = null;
                }
                return this;
            }

            public PairingSecret.Builder getPairingSecretBuilder() {
                onChanged();
                return getPairingSecretFieldBuilder().getBuilder();
            }

            @Override
            public PairingSecretOrBuilder getPairingSecretOrBuilder() {
                SingleFieldBuilderV3<PairingSecret, PairingSecret.Builder, PairingSecretOrBuilder> singleFieldBuilderV3 = this.pairingSecretBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                PairingSecret pairingSecret = this.pairingSecret_;
                return pairingSecret == null ? PairingSecret.getDefaultInstance() : pairingSecret;
            }

            private SingleFieldBuilderV3<PairingSecret, PairingSecret.Builder, PairingSecretOrBuilder> getPairingSecretFieldBuilder() {
                if (this.pairingSecretBuilder_ == null) {
                    this.pairingSecretBuilder_ = new SingleFieldBuilderV3<>(getPairingSecret(), getParentForChildren(), isClean());
                    this.pairingSecret_ = null;
                }
                return this.pairingSecretBuilder_;
            }

            @Override
            public boolean hasPairingSecretAck() {
                return (this.pairingSecretAckBuilder_ == null && this.pairingSecretAck_ == null) ? false : true;
            }

            @Override
            public PairingSecretAck getPairingSecretAck() {
                SingleFieldBuilderV3<PairingSecretAck, PairingSecretAck.Builder, PairingSecretAckOrBuilder> singleFieldBuilderV3 = this.pairingSecretAckBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                PairingSecretAck pairingSecretAck = this.pairingSecretAck_;
                return pairingSecretAck == null ? PairingSecretAck.getDefaultInstance() : pairingSecretAck;
            }

            public Builder setPairingSecretAck(PairingSecretAck pairingSecretAck) {
                SingleFieldBuilderV3<PairingSecretAck, PairingSecretAck.Builder, PairingSecretAckOrBuilder> singleFieldBuilderV3 = this.pairingSecretAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Objects.requireNonNull(pairingSecretAck);
                    this.pairingSecretAck_ = pairingSecretAck;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(pairingSecretAck);
                }
                return this;
            }

            public Builder setPairingSecretAck(PairingSecretAck.Builder builder) {
                SingleFieldBuilderV3<PairingSecretAck, PairingSecretAck.Builder, PairingSecretAckOrBuilder> singleFieldBuilderV3 = this.pairingSecretAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.pairingSecretAck_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergePairingSecretAck(PairingSecretAck pairingSecretAck) {
                SingleFieldBuilderV3<PairingSecretAck, PairingSecretAck.Builder, PairingSecretAckOrBuilder> singleFieldBuilderV3 = this.pairingSecretAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    PairingSecretAck pairingSecretAck2 = this.pairingSecretAck_;
                    if (pairingSecretAck2 != null) {
                        this.pairingSecretAck_ = PairingSecretAck.newBuilder(pairingSecretAck2).mergeFrom(pairingSecretAck).buildPartial();
                    } else {
                        this.pairingSecretAck_ = pairingSecretAck;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(pairingSecretAck);
                }
                return this;
            }

            public Builder clearPairingSecretAck() {
                if (this.pairingSecretAckBuilder_ == null) {
                    this.pairingSecretAck_ = null;
                    onChanged();
                } else {
                    this.pairingSecretAck_ = null;
                    this.pairingSecretAckBuilder_ = null;
                }
                return this;
            }

            public PairingSecretAck.Builder getPairingSecretAckBuilder() {
                onChanged();
                return getPairingSecretAckFieldBuilder().getBuilder();
            }

            @Override
            public PairingSecretAckOrBuilder getPairingSecretAckOrBuilder() {
                SingleFieldBuilderV3<PairingSecretAck, PairingSecretAck.Builder, PairingSecretAckOrBuilder> singleFieldBuilderV3 = this.pairingSecretAckBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                PairingSecretAck pairingSecretAck = this.pairingSecretAck_;
                return pairingSecretAck == null ? PairingSecretAck.getDefaultInstance() : pairingSecretAck;
            }

            private SingleFieldBuilderV3<PairingSecretAck, PairingSecretAck.Builder, PairingSecretAckOrBuilder> getPairingSecretAckFieldBuilder() {
                if (this.pairingSecretAckBuilder_ == null) {
                    this.pairingSecretAckBuilder_ = new SingleFieldBuilderV3<>(getPairingSecretAck(), getParentForChildren(), isClean());
                    this.pairingSecretAck_ = null;
                }
                return this.pairingSecretAckBuilder_;
            }

            @Override
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }

        public static PairingMessage getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<PairingMessage> parser() {
            return PARSER;
        }

        @Override
        public Parser<PairingMessage> getParserForType() {
            return PARSER;
        }

        @Override
        public PairingMessage getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        Descriptors.Descriptor descriptor2 = getDescriptor().getMessageTypes().get(0);
        internal_static_com_kunal52_pairing_PairingRequest_descriptor = descriptor2;
        internal_static_com_kunal52_pairing_PairingRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"ClientName", "ServiceName"});
        Descriptors.Descriptor descriptor3 = getDescriptor().getMessageTypes().get(1);
        internal_static_com_kunal52_pairing_PairingRequestAck_descriptor = descriptor3;
        internal_static_com_kunal52_pairing_PairingRequestAck_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"ServerName"});
        Descriptors.Descriptor descriptor4 = getDescriptor().getMessageTypes().get(2);
        internal_static_com_kunal52_pairing_PairingEncoding_descriptor = descriptor4;
        internal_static_com_kunal52_pairing_PairingEncoding_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"Type", "SymbolLength"});
        Descriptors.Descriptor descriptor5 = getDescriptor().getMessageTypes().get(3);
        internal_static_com_kunal52_pairing_PairingOption_descriptor = descriptor5;
        internal_static_com_kunal52_pairing_PairingOption_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor5, new String[]{"InputEncodings", "OutputEncodings", "PreferredRole"});
        Descriptors.Descriptor descriptor6 = getDescriptor().getMessageTypes().get(4);
        internal_static_com_kunal52_pairing_PairingConfiguration_descriptor = descriptor6;
        internal_static_com_kunal52_pairing_PairingConfiguration_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor6, new String[]{"Encoding", "ClientRole"});
        Descriptors.Descriptor descriptor7 = getDescriptor().getMessageTypes().get(5);
        internal_static_com_kunal52_pairing_PairingConfigurationAck_descriptor = descriptor7;
        internal_static_com_kunal52_pairing_PairingConfigurationAck_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor7, new String[0]);
        Descriptors.Descriptor descriptor8 = getDescriptor().getMessageTypes().get(6);
        internal_static_com_kunal52_pairing_PairingSecret_descriptor = descriptor8;
        internal_static_com_kunal52_pairing_PairingSecret_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor8, new String[]{"Secret"});
        Descriptors.Descriptor descriptor9 = getDescriptor().getMessageTypes().get(7);
        internal_static_com_kunal52_pairing_PairingSecretAck_descriptor = descriptor9;
        internal_static_com_kunal52_pairing_PairingSecretAck_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor9, new String[]{"Secret"});
        Descriptors.Descriptor descriptor10 = getDescriptor().getMessageTypes().get(8);
        internal_static_com_kunal52_pairing_PairingMessage_descriptor = descriptor10;
        internal_static_com_kunal52_pairing_PairingMessage_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor10, new String[]{"ProtocolVersion", "Status", "RequestCase", "PairingRequest", "PairingRequestAck", "PairingOption", "PairingConfiguration", "PairingConfigurationAck", "PairingSecret", "PairingSecretAck"});
    }
}
